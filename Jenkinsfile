pipeline {
	environment {
	    registry = "saturn203/my-app"
	    registryCredential = 'dockerhub'
	  }
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
        
        stage('Building image') {
            steps {
               script {
		          docker.build registry + ":$BUILD_NUMBER"
		        }
            }
        }
        stage('Deploy Image') {
		  steps{
		    script {
		      docker.withRegistry( '', registryCredential ) {
		        dockerImage.push()
		      }
		    }
		  }
		}
		
		stage('Remove Unused docker image') {
		  steps{
		    sh "docker rmi $registry:$BUILD_NUMBER"
		  }
		}
    }
}
