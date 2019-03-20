pipeline {
	environment {
	    registry = "saturn203/my-app"
	    registryCredential = 'dockerhub'
	  }
    agent any
    tools {
        maven 'Maven 3.5.0'
        
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
		          dockerImage = docker.build registry + ":$BUILD_NUMBER"
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
