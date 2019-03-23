



pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	 
	    dockerHome = tool 'docker'        
        PATH = "$PATH:${dockerHome}/bin:${env.PATH}" 
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
               
		       sh "docker build -t $registry:$BUILD_NUMBER ."
		        
            }
        }       
        
        
                
       
    }
}
