void call() {
emailext(       attachLog: true, attachmentsPattern: 'Release_Status.csv',
                subject: "${env.JOB_NAME}: [${env.BUILD_NUMBER}]",
                to: 'monikalevale@gmail.com',               
                body: "PFA"
            )
}
