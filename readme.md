# Email microservice

This service receives `POST` requests with content of an email, and then dispatch this email to the receiver. Once the email is sent (or at least attempted to be), the information is saved on a Postgres DB for later consume. So for example if an email gets an error for any reason, it is possible to re-send it latter.
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

Returns a list with all the emails saved on the database, paginated.

```json
{
  "content": [
    {
      "emailId": " ",
      "ownerRef": " ",
      "emailFrom": " ",
      "emailTo": " ",
      "subject": "" ,
      "text": " ",
      "sendDateEmail": " ",
      "statusEmail": " "
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "sorted": true,
      "unsorted": false
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 5,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "number": 0,
  "sort": {
    "empty": false,
    "sorted": true,
    "unsorted": false
  },
  "size": 5,
  "first": true,
  "numberOfElements": 1,
  "empty": false
}
```