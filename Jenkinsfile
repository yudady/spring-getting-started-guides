pipeline {
	agent none
	stages {
		stage('Back-end') {
			agent {
				docker { image 'maven:3-alpine' }
			}
			steps {
				checkout(
						[$class           : 'GitSCM',
						 branches         : [[name: '*/main']],
						 extensions       : [],
						 userRemoteConfigs: [[url: 'https://github.com/yudady/spring-getting-started-guides.git']
						 ]
						]
				)

				sh 'cd 01-gs-rest-service'


				sh 'mvn --version'
			}
		}
		stage('Front-end') {
			agent {
				docker { image 'node:7-alpine' }
			}
			steps {
				sh 'node --version'
			}
		}
	}
}