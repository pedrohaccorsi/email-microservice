# Email microservice

This service dispatches emails. Once the email is sent (or at least attempted to be), the information is saved on a Postgres DB for later consume. So for example if an email gets an error for any reason, it is possible to re-send it latter.

## Asynchronous queues

This service has a consumer to a managed RabbitMQ queue called `email-ms`. This queue can receive messages from other services, which will be consumed automatically by the email service and send the email.
The payload expected for the queue is the same for the public endpoints:

```json
{
  "ownerRef"  : "Who is creating this email",
  "emailFrom" : "email address that has SMTP configured in application.properties",
  "emailTo"   : "email target",
  "subject"   : "email subject",
  "text"      : "email body"
}
```

## Endpoints

### POST /sending-email

Receives a `JSON` object in the request's body, as shown below:

```json
{
  "ownerRef"  : "Who is creating this email",
  "emailFrom" : "email address that has SMTP configured in application.properties",
  "emailTo"   : "email target",
  "subject"   : "email subject",
  "text"      : "email body"
}
```

After the email is sent, the endpoint returns the following response:

```json
{
  "emailId": "UUID of this email in the database",
  "ownerRef"  : "Who is creating this email",
  "emailFrom" : "email address that has SMTP configured in application.properties",
  "emailTo"   : "email target",
  "subject"   : "email subject",
  "subject"   : "email subject",
  "sendDateEmail": "date and moment that the email was sent",
  "statusEmail": "SENT or ERROR"
}
```

### GET /emails

Returns a list with all the emails saved on the database, paginated for easier integration with a UI.

## Database

All emails are saved on a Postgres DB, that must be running before the service. It has only one table called `TB_EMAIL`, with the following columns:

| id | email_from | email_to | owner_ref | send_date_email | status_email | subject | text | 
|----|------------|----------|-----------|-----------------|--------------|---------|------|
| UUID [pk] | varchar(255) | varchar(255) | varchar(255) | timestamp without time zone | integer | varchar(255) | text| 


## How to run locally

Clone the repo and create the `.env` and `applications.properties` files, as the ones available here are only examples due to sensitive data that they store. After that, it is important to configure the `smtp` email password and the `rabbimq` URI. With these done, you can proceed.

1. run docker-compose.yml
2. start the service