



pipeline {
	
	environment {
	    registry = "asia.gcr.io/my-gcp101/my-app"	  
	}
	
	

    agent { docker 'maven:3-alpine' }
    
     
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
