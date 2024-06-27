pipelineJob('pipeline') {
    definition {
        cps {
            script('''pipeline {
                agent {
                    docker {
                        image 'pipeline/jenkins'
                        args '-u 0:0 -v /var/run/docker.sock:/var/run/docker.sock'
                    }
                }
                environment {
                    registry = "samarmatoussi/jcasc"
                    registryCredential = 'dockerHub'
                    dockerImage = ""
                }                    
                stages {
                    stage('git') {
                        steps {
                            echo 'Cloning the repository'
                            git branch: 'master', credentialsId: 'github-credentials', url: 'https://github.com/SamarMatoussi/react-helloWorld.git'
                            sh 'docker run hello-world'
                        }
                    }
                    stage('Install Dependencies') {
                        steps {
                            sh 'npm install'
                        }
                    }
                }
            }''')
        }
    }
}

