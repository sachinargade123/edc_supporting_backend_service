# EDC Proxy Service

![Version: 0.1.1](https://img.shields.io/badge/Version-0.1.1-informational?style=flat-square) ![Type: application](https://img.shields.io/badge/Type-application-informational?style=flat-square) ![AppVersion: 0.1.1](https://img.shields.io/badge/AppVersion-0.1.1-informational?style=flat-square)

Data exchange service is used to exchange the data between connectors

## Source Code

* <https://github.com/sachinargade123/edc_supporting_backend_service>

## Values

| Key | Type | Default | Description |
|-----|------|---------|-------------|
| affinity | object | `{}` |  |
| autoscaling.enabled | bool | `false` |  |
| autoscaling.maxReplicas | int | `100` |  |
| autoscaling.minReplicas | int | `1` |  |
| autoscaling.targetCPUUtilizationPercentage | int | `10092` |  |
| fullnameOverride | string | `""` |  |
| image.pullPolicy | string | `"Always"` | Set the Image Pull Policy |
| image.repository | string | `"ghcr.io/sachinargade123/edc_supporting_backend_service/edcbackendproxy"` | Image to use for deploying an application |
| image.tag | string | `""` | Image tage is defined in chart appVersion. |
| imagePullSecrets | list | `[]` |  |
| ingress.annotations."cert-manager.io/cluster-issuer" | string | `"letsencrypt-prod"` |  |
| ingress.annotations."nginx.ingress.kubernetes.io/use-regex" | string | `"true"` |  |
| ingress.className | string | `""` | a reference to an Ingress Class resource that contains additional configuration including the name of the controller that should implement the class. |
| ingress.enabled | bool | `false` | If you want to enable or disable the ingress |
| ingress.hosts[0] | object | `{"host":"","paths":[{"path":"/","pathType":"ImplementationSpecific"}]}` | Host of the application on which application runs |
| ingress.hosts[0].paths[0].pathType | string | `"ImplementationSpecific"` | ImplementationSpecific path type matching is up to the IngressClass. Implementations can treat this as a separate pathType or treat it identically to Prefix or Exact path types. |
| ingress.tls.enabled | bool | `false` |  |
| ingress.tls.secretName | string | `"tls-secret"` |  |
| nameOverride | string | `""` |  |
| nodeSelector | object | `{}` |  |
| podAnnotations | object | `{}` |  |
| podSecurityContext | object | `{}` |  |
| replicaCount | int | `1` | Number of Replicas for pods |
| resources.limits.cpu | string | `"500m"` |  |
| resources.limits.memory | string | `"400Mi"` |  |
| resources.requests.cpu | string | `"200m"` |  |
| resources.requests.memory | string | `"300Mi"` |  |
| securityContext.allowPrivilegeEscalation | bool | `false` | Controls whether a process can gain more privileges |
| securityContext.capabilities.drop[0] | string | `"ALL"` |  |
| service.port | int | `10092` | Port details for sevice |
| service.targetPort | int | `10092` | Container Port details for sevice |
| service.type | string | `"ClusterIP"` | Type of service |
| serviceAccount.annotations | object | `{}` |  |
| serviceAccount.create | bool | `true` |  |
| serviceAccount.name | string | `""` |  |
| tolerations | list | `[]` |  |

----------------------------------------------
Autogenerated from chart metadata using [helm-docs v1.11.0](https://github.com/norwoodj/helm-docs/releases/v1.11.0)
