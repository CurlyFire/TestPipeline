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

}

