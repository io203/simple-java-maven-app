podTemplate(label: 'test-k8s', cloud: 'local cluster', 
    containers: [
        containerTemplate(name: 'test', image: 'gcr.io/cloud-solutions-images/jenkins-k8s-slave:latest', ttyEnabled: true, command: 'cat')    
    ],
    volumes: [
        hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock'),
        hostPathVolume(hostPath: '/usr/bin/docker', mountPath: '/usr/bin/docker')
    ]
  ) {
    node('test-k8s') {
        container('test') {
            stage('Run Command') {

                checkout scm
                
                sh("docker version");
                sh 'gcloud version'

            }
        }
    }
}