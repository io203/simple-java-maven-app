podTemplate(
    
    node ('jenkins-pipeline') {
      stage('Get latest version of code') {
        checkout scm
      }

      
      
      stage('Deploy Local') {
        println 'Building docker image'
        container('docker') { 
          sh "echo \$'FROM nginx:stable-alpine \nCOPY ./dist /usr/share/nginx/html \n' > Dockerfile"
          sh 'docker version' 
        }

        
      } //end deploy local

      

      stage('Deploy Production') {
      }

      stage('Run Post Deployment Tests') {
      }
    } // end node
} // end podTemplate