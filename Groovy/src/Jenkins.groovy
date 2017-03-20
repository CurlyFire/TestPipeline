#!groovy
node('')
{
    stage('Checkout')
    {
        echo( 'Hello $$$ world!')
        git credentialsId: '289766ef-e84f-4e38-91f6-4984b6dd6594', url: 'https://github.com/CurlyFire/TestPipeline.git'
    }
    stage('Set file versions')
    {
        sh('for f in $(find -name \'project.json\'); do cat $f | jq --arg v ${version} \'.version = $v\' > temp.json && mv temp.json $f ; done')
    }
}

