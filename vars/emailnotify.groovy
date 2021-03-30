void call() {
emailext(      
                subject: "${currentBuild.result}: ${env.JOB_NAME} [${env.BUILD_NUMBER}]",
                to: 'monikalevale@gmail.com',               
                body: "PFA"
            )
}
