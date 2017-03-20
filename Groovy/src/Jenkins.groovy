#!groovy

stage("Checkout")
{
    echo( "Hello world!")
    git credentialsId: '289766ef-e84f-4e38-91f6-4984b6dd6594', url: 'https://github.com/CurlyFire/TestPipeline.git'
}
