pipeline {
	environment {
	    registry = "saturn203/my-app"
	    registryCredential = 'dockerhub'
	  }
    agent any
    
    stages {
        stage('Build') {
            agent {
                docker { image 'maven:3-alpine' }
            }
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
		
		
    }
}
