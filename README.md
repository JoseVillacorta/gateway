# API Gateway

Puerta de enlace principal del sistema de microservicios para gestión de pedidos.

## Funcionalidades

- **Enrutamiento**: Distribuye requests a microservicios apropiados
- **Balanceo de carga**: Usa Spring Cloud LoadBalancer con Eureka
- **Descubrimiento de servicios**: Integración automática con Eureka
- **Configuración centralizada**: Gestionado por Config Server

## Endpoints

- `GET/POST/PUT/DELETE /productos/**` → Redirige a ms-productos
- `GET/POST/PUT/DELETE /pedidos/**` → Redirige a ms-pedidos (futuro)

## Configuración Docker

- **Puerto**: 8083
- **Perfil**: docker
- **Eureka**: `registry-service:8761`
- **Seguridad**: Deshabilitada para desarrollo

## Rutas Configuradas

```yaml
routes:
  - id: ms-productos
    uri: http://ms-productos:8081
    predicates:
      - Path=/productos/**
```

## Despliegue

```bash
docker-compose up --build gateway
```

## Health Check

- Endpoint: `http://localhost:8083/actuator/health`
- Estado esperado: `{"status":"UP"}`