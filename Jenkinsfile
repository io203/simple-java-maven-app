pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /Users/blackstar/.m2:/root/.m2'
        }
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }        
        
        stage('Docker-Image') {
            steps {
                sh 'docker build -t saturn203/my-app:v1 .'
            }
        }
    }
}
