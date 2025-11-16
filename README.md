# Gateway Service

API Gateway con Spring Cloud Gateway para enrutamiento dinámico, load balancing y gestión centralizada de tráfico.

## Descripción
Gateway Service actúa como punto de entrada único para todos los microservicios, proporcionando:

- **Enrutamiento Dinámico**: Rutas configurables hacia microservicios registrados en Eureka
- **Load Balancing**: Balanceo automático de carga entre instancias de servicios
- **Service Discovery**: Integración con Eureka para descubrimiento automático
- **Configuración Centralizada**: Configuración via Spring Cloud Config Server
- **Monitoring y Observabilidad**: Métricas, health checks y logging centralizado
- **Rate Limiting**: Control de velocidad de requests (configurable)
- **Circuit Breakers**: Protección contra fallos en cascada


### Funcionalidades Principales

#### **Load Balancing**
- Balanceo round-robin automático
- Health-based routing (solo hacia servicios saludables)
- Failover automático entre instancias

#### **Service Discovery**
- Registro automático en Eureka
- Resolución dinámica de nombres de servicios
- Hot-reload de rutas sin restart

#### **Configuración Dinática**
- Actualización automática desde Config Server
- Perfiles por entorno (dev/qa/prod)
- Refresh de configuración via `/actuator/refresh`

## Dependencias

### Servicios de Infraestructura
- **Registry Service**: http://localhost:8761 (Eureka Server)
- **Config Server**: http://localhost:8888 (Spring Cloud Config)
- **MS Productos V2**: http://localhost:8083 (ms-productos-v2)
- **MS Pedidos**: http://localhost:8082 (ms-pedidos)

## Ejecutar

### Desarrollo Local
```bash
./gradlew bootRun --spring.profiles.active=dev
```

## Métricas y Monitoreo

### Endpoints de Actuator
```bash
# Health check completo
curl http://localhost:8080/actuator/health

# Métricas del Gateway
curl http://localhost:8080/actuator/metrics

# Métricas Prometheus
curl http://localhost:8080/actuator/prometheus

# Información del servicio
curl http://localhost:8080/actuator/info

# Lista de rutas
curl http://localhost:8080/actuator/gateway/routes

# Refresh de configuración
curl -X POST http://localhost:8080/actuator/refresh
```

### Métricas Disponibles
- **gateway.requests**: Contador de requests procesados
- **gateway.responses**: Respuestas por código de estado
- **gateway.routes**: Rutas activas
- **http.server.requests**: Latencia y throughput

## Integración con otros servicios

### Con MS Productos V2
- **Puerto destino**: 8083
- **Servicio**: ms-productos-v2 (Eureka)
- **Ruta**: `/api/products/**`

### Con MS Pedidos
- **Puerto destino**: 8082
- **Servicio**: ms-pedidos (Eureka)
- **Ruta**: `/api/pedidos/**`

### Con Registry Service
- **Registro**: Automático en Eureka
- **URL**: http://localhost:8761/eureka/apps/gateway-service
- **Health Check**: Integrado con Eureka

### Con Config Server
- **URL**: http://localhost:8888/gateway-service/dev
- **Configuración**: `config-repo/gateway-service.yaml`
- **Auto Refresh**: Via `/actuator/refresh`
