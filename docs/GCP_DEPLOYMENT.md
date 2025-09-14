# GCP Deployment Guide

This guide covers deploying the Wrestling Application to Google Cloud Platform.

## Prerequisites

1. Google Cloud Platform account
2. Google Cloud SDK (gcloud) installed
3. Docker installed (for containerized deployment)

## Database Setup (Cloud SQL)

### 1. Create PostgreSQL Instance
```bash
gcloud sql instances create wrestling-db-instance \
    --database-version=POSTGRES_13 \
    --tier=db-f1-micro \
    --region=us-central1
```

### 2. Create Database
```bash
gcloud sql databases create wrestling_db --instance=wrestling-db-instance
```

### 3. Create Database User
```bash
gcloud sql users create wrestling_user \
    --instance=wrestling-db-instance \
    --password=your-secure-password
```

## Backend Deployment (App Engine)

### 1. Update Configuration
Edit `backend/src/main/appengine/app.yaml`:
```yaml
runtime: java17

env_variables:
  SPRING_PROFILES_ACTIVE: prod
  INSTANCE_CONNECTION_NAME: your-project-id:us-central1:wrestling-db-instance
  DB_USER: wrestling_user
  DB_PASS: your-secure-password
  JWT_SECRET: your-jwt-secret-256-bit-key
  GCS_BUCKET_NAME: your-wrestling-app-bucket
  FRONTEND_URL: https://your-frontend-domain.com

automatic_scaling:
  min_instances: 1
  max_instances: 10
  target_cpu_utilization: 0.6
```

### 2. Deploy Backend
```bash
cd backend
mvn clean package -DskipTests
gcloud app deploy src/main/appengine/app.yaml
```

## Frontend Deployment

### Option 1: Firebase Hosting
```bash
cd frontend
npm install -g firebase-tools
firebase login
firebase init hosting
npm run build
firebase deploy
```

### Option 2: App Engine (Static)
Create `frontend/app.yaml`:
```yaml
runtime: nodejs18
handlers:
  - url: /.*
    static_files: dist/index.html
    upload: dist/.*
```

Deploy:
```bash
cd frontend
npm run build
gcloud app deploy app.yaml
```

## Cloud Storage Setup

### 1. Create Storage Bucket
```bash
gsutil mb gs://your-wrestling-app-bucket
```

### 2. Set Bucket Permissions
```bash
gsutil iam ch allUsers:objectViewer gs://your-wrestling-app-bucket
```

## Environment Variables

Set the following environment variables in your GCP project:

- `GOOGLE_CLOUD_PROJECT`: Your GCP project ID
- `INSTANCE_CONNECTION_NAME`: Cloud SQL connection name
- `DB_USER`: Database username
- `DB_PASS`: Database password
- `JWT_SECRET`: JWT signing secret
- `GCS_BUCKET_NAME`: Cloud Storage bucket name
- `FRONTEND_URL`: Frontend application URL

## Security Configuration

### 1. Enable Required APIs
```bash
gcloud services enable sqladmin.googleapis.com
gcloud services enable storage.googleapis.com
gcloud services enable appengine.googleapis.com
```

### 2. Create Service Account (if needed)
```bash
gcloud iam service-accounts create wrestling-app-sa \
    --display-name="Wrestling App Service Account"
```

### 3. Grant Permissions
```bash
gcloud projects add-iam-policy-binding your-project-id \
    --member="serviceAccount:wrestling-app-sa@your-project-id.iam.gserviceaccount.com" \
    --role="roles/cloudsql.client"
```

## Monitoring and Logging

### Enable Logging
- Application logs are automatically available in Cloud Logging
- Access logs at: https://console.cloud.google.com/logs

### Enable Monitoring
- Performance metrics available in Cloud Monitoring
- Set up alerts for high CPU/memory usage
- Monitor database connections and query performance

## Production Checklist

- [ ] Database connection configured
- [ ] Environment variables set
- [ ] Security rules configured
- [ ] SSL certificates configured
- [ ] Monitoring and alerting set up
- [ ] Backup strategy implemented
- [ ] Load testing completed
- [ ] Error handling tested

## Troubleshooting

### Common Issues

1. **Database Connection Errors**
   - Verify Cloud SQL instance is running
   - Check connection string format
   - Ensure service account has proper permissions

2. **Memory/CPU Issues**
   - Increase instance class in app.yaml
   - Optimize database queries
   - Enable connection pooling

3. **Storage Access Issues**
   - Verify bucket exists and is accessible
   - Check IAM permissions
   - Ensure CORS is configured properly

## Cost Optimization

- Use Cloud SQL proxy for secure connections
- Implement connection pooling
- Set up auto-scaling rules
- Monitor usage with Cloud Billing
- Use preemptible instances for development

## Backup and Recovery

### Database Backups
```bash
gcloud sql backups create --instance=wrestling-db-instance
```

### Storage Backups
```bash
gsutil -m cp -r gs://your-wrestling-app-bucket gs://your-backup-bucket
```