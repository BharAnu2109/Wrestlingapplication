# Wrestling Application

A comprehensive wrestling management system built with Spring Boot backend and Angular frontend, designed for deployment on Google Cloud Platform.

## Features

### Current Implementation
- **Wrestler Management**: Complete CRUD operations for wrestler profiles
- **Weight Categories**: Support for all official wrestling weight categories (Men's and Women's)
- **Match Tracking**: Match scheduling, scoring, and result management
- **Tournament Organization**: Tournament creation and management
- **Statistics & Rankings**: Wrestler performance tracking and rankings
- **Responsive UI**: Bootstrap-powered responsive design

### Wrestling Scenarios Covered
- Wrestler registration and profile management
- Weight category classification
- Match scheduling and result recording
- Tournament organization and bracket management
- Performance statistics and ranking systems
- Multi-style support (Freestyle, Greco-Roman, Folkstyle)

## Technology Stack

### Backend
- **Java 17** with Spring Boot 3.2.0
- **Spring Data JPA** for database operations
- **Spring Security** for authentication and authorization
- **H2 Database** for development
- **PostgreSQL** for production (GCP Cloud SQL)
- **Maven** for dependency management

### Frontend
- **Angular 17** with TypeScript
- **Bootstrap 5** for responsive UI
- **Font Awesome** for icons
- **Angular Material** for enhanced components

### Cloud Platform
- **Google Cloud Platform (GCP)**
- **App Engine** for application hosting
- **Cloud SQL** for managed PostgreSQL database
- **Cloud Storage** for file management

## Quick Start

### Prerequisites
- Java 17+
- Node.js 18+
- Maven 3.6+
- npm/yarn

### Backend Setup
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
Backend will start on `http://localhost:8080`

### Frontend Setup
```bash
cd frontend
npm install
npm start
```
Frontend will start on `http://localhost:4200`

## API Endpoints

### Wrestlers API
- `GET /api/wrestlers` - Get all wrestlers (paginated)
- `GET /api/wrestlers/{id}` - Get wrestler by ID
- `POST /api/wrestlers` - Create new wrestler
- `PUT /api/wrestlers/{id}` - Update wrestler
- `DELETE /api/wrestlers/{id}` - Delete wrestler
- `GET /api/wrestlers/search?name={name}` - Search wrestlers
- `GET /api/wrestlers/weight-category/{category}` - Get wrestlers by weight category
- `GET /api/wrestlers/top/wins` - Get top wrestlers by wins
- `GET /api/wrestlers/top/points` - Get top wrestlers by points

### Database Access
- H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:wrestlingdb`
- Username: `sa`
- Password: `password`

## Sample Data

The application comes with pre-loaded sample data including:
- 5 wrestlers from different countries and weight categories
- 2 sample tournaments (World Championship 2024, Olympic Games 2024)
- Realistic statistics and rankings

## GCP Deployment

### Configuration Files
- `backend/src/main/appengine/app.yaml` - App Engine configuration
- `backend/src/main/resources/application-prod.properties` - Production configuration

### Environment Variables (GCP)
```
SPRING_PROFILES_ACTIVE=prod
INSTANCE_CONNECTION_NAME=your-project:your-region:wrestling-db-instance
DB_USER=wrestling_user
DB_PASS=your-password
JWT_SECRET=your-jwt-secret-key-here
GCS_BUCKET_NAME=your-wrestling-app-bucket
FRONTEND_URL=https://your-frontend-domain.com
```

### Deployment Commands
```bash
# Deploy backend to App Engine
cd backend
gcloud app deploy src/main/appengine/app.yaml

# Build and deploy frontend
cd frontend
npm run build
# Deploy to your preferred hosting service
```

## Architecture

### Backend Architecture
```
├── entity/          # JPA entities (Wrestler, Match, Tournament)
├── repository/      # Spring Data repositories
├── service/         # Business logic layer
├── controller/      # REST API controllers
├── config/          # Configuration classes
└── security/        # Security configuration
```

### Frontend Architecture
```
├── components/      # Angular components
├── services/        # HTTP services
├── models/          # TypeScript interfaces
├── shared/          # Shared utilities
└── assets/          # Static assets
```

## Development Guidelines

### Backend
- Follow RESTful API design principles
- Use Spring Boot best practices
- Implement proper error handling
- Add validation annotations
- Write unit tests for services

### Frontend
- Use Angular standalone components
- Implement reactive forms
- Follow Angular style guide
- Use TypeScript strictly
- Implement proper error handling

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support and questions, please create an issue in the GitHub repository.