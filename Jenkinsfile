



pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	 
	    dockerHome = tool 'docker'        
        PATH = "$PATH:${dockerHome}/bin:${env.PATH}" 
        registryCredential = 'gcr-docker-auth'
	}
	
	agent any
	
	tools {
        maven "maven"
    }
     
    stages {
        stage('Build') {            	   
        	steps {
                sh "mvn -B -DskipTests clean package"
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
		   	sh '''
                echo '----1----'
                docker login -u oauth2accesstoken -p "$(gcloud auth print-access-token)" https://asia.gcr.io
                echo '-----2----------'
                docker push  $registry:$BUILD_NUMBER
                echo '----3------'
              '''
		  }
		}
		stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }
           
        
        
                
       
    }
}
