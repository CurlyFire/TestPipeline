#!groovy
node('')
{
    stage('Checkout')
    {
        git credentialsId: '289766ef-e84f-4e38-91f6-4984b6dd6594', url: 'https://github.com/CurlyFire/TestPipeline.git'
    }

    stage('Set version')
    {
        sh('for f in $(find -name \'project.json\'); do cat $f | jq --arg v ${version} \'.version = $v\' > temp.json && mv temp.json $f ; done')
    }

    stage('Restore')
    {
        sh('dotnet restore --configfile ~/.config/NuGet/NuGet.Config')
    }

    stage('Build')
    {
        sh('dotnet build **/project.json')
    }

    stage('Tests')
    {
        sh('for d in $(grep -r -e "testRunner" -l --include project.json | xargs dirname); do (cd $d && dotnet test); done')
    }

    stage('Publish')
    {
        sh('''cd $WORKSPACE/Presentation.Web 
              dotnet publish -o Docker/output''')
    }
    stage('Docker build')
    {
        docker.withServer('tcp://192.168.2.16:4243')
        {
            docker.build('inspq/presentation.web','Presentation.Web/Docker')
        }
    }
}

