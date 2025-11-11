# API Gateway

Puerta de enlace principal del sistema de microservicios para gestión de pedidos.

## Funcionalidades

- **Enrutamiento**: Distribuye requests a microservicios apropiados
- **Balanceo de carga**: Usa Spring Cloud LoadBalancer con Eureka
- **Descubrimiento de servicios**: Integración automática con Eureka
- **Configuración centralizada**: Gestionado por Config Server
- **Autenticación OAuth2**: Cliente OAuth2 con login automático
- **Protección de rutas**: Todas las rutas requieren autenticación JWT

## Endpoints

### Autenticación OAuth2
- `GET /oauth2/authorization/oauth-client` → Inicia flujo de login OAuth2
- `GET /authorized` → Callback que recibe y procesa tokens JWT

### APIs Protegidas (requieren Bearer Token)
- `GET/POST/PUT/DELETE /productos/**` → Redirige a ms-productos

## Configuración Docker

- **Puerto**: 8083
- **Perfil**: docker
- **Eureka**: `registry-service:8761`
- **OAuth2 Client**: Configurado como `oauth-client`
- **Provider**: `oauth-server:9000`
- **Seguridad**: OAuth2 habilitado con JWT

## Rutas Configuradas

```yaml
routes:
  - id: ms-productos
    uri: lb://ms-productos
    predicates:
      - Path=/productos/**

# Cliente OAuth2 configurado
registration:
  oauth-client:
    provider: oauth-server
    client-id: oauth-client
    client-secret: 12345678910
    authorization-grant-type: authorization_code
    redirect-uri: http://localhost:8083/authorized
```

## Despliegue

```bash
docker-compose up --build gateway
```

## Health Check

- Endpoint: `http://localhost:8083/actuator/health`
- Estado esperado: `{"status":"UP"}`