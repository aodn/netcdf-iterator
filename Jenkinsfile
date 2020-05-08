pipeline {
    agent {
        dockerfile {
            args '-v ${HOME}/.m2:/home/builder/.m2 -v ${HOME}/bin:${HOME}/bin'
            additionalBuildArgs '--build-arg BUILDER_UID=$(id -u)'
        }
    }
    stages {
        stage('test') {
            steps {
                sh 'mvn -B clean test'
            }
        }
        stage('package') {
            steps {
                sh 'mvn -B package'
            }
        }
        stage('deploy') {
            when { branch "master" }
            steps {
                sh 'mvn -B deploy'
            }
        }
    }
    post {
        success {
            dir('target/') {
                archiveArtifacts artifacts: 'target/netcdf-iterator-*-SNAPSHOT.jar', fingerprint: true, onlyIfSuccessful: true
            }
        }
    }
}
