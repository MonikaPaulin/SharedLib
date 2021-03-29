void call() {
emailext attachLog: true, attachmentsPattern: 'Release_Status.csv'
body: 'Attached is the release report log file.', 
replyTo: 'MonikaPaulin.Jeyakumar@cognizant.com', subject: 'Release Report Jenkins Pipeline', to: 'MonikaPaulin.Jeyakumar@cognizant.com'
}
