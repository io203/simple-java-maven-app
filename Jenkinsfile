



pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	 
	    dockerHome = tool 'docker'    
	    gcloudHome = tool 'gcloud'         
        PATH = "$PATH:${dockerHome}/bin:${gcloudHome}/bin:${env.PATH}" 
        registryCredential = 'gcr-docker-auth'
	}
	
	agent any
	
	tools {
        maven "maven"
        gcloud "gcloud"
    }
     
    stages {
        stage('Build') {            	   
        	steps {
                sh "mvn -B -DskipTests clean package"
            }
        } 
        
        stage('Building image') {
            steps {
               sh "docker build -t $registry:$BUILD_NUMBER ."
            }
        }
        stage('Deploy Image') {
		  steps{
		  	sh "docker login -u oauth2accesstoken -p ya29.GlzVBuh2ncMceaJMPm2SVduaR4q2mzzQvG89yRO8Moo88FF1F0gb1ITz43ez2Uq_R1_H39vKQn1In-kbwg7LlysfuLyc6FW3wfd3op8cNBfVhv7Q3w1vP7hV0xdHPA  https://asia.gcr.io" 
		  	sh "docker push $registry:$BUILD_NUMBER"
		  	
		    
		  }
		}
		stage('Remove Unused docker image') {
          steps{
            sh "docker rmi $registry:$BUILD_NUMBER"
          }
        }
           
        
        
                
       
    }
}
