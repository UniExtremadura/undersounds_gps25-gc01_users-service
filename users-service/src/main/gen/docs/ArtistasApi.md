# ArtistasApi

All URIs are relative to *http://localhost:8080/api*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**artistDelete**](ArtistasApi.md#artistDelete) | **DELETE** /artist | Realiza un borrado lógico de un artista autenticado |
| [**artistIdRestauracionPatch**](ArtistasApi.md#artistIdRestauracionPatch) | **PATCH** /artist/{id}/restauracion | Restaura a un artista previamente eliminado de forma lógica |
| [**artistPatch**](ArtistasApi.md#artistPatch) | **PATCH** /artist | Actualiza los datos de un artista autenticado |
| [**artistPaymentInfoGet**](ArtistasApi.md#artistPaymentInfoGet) | **GET** /artist/payment-info | Obtiene toda la información financiera de un artista autenticado. |
| [**artistPost**](ArtistasApi.md#artistPost) | **POST** /artist | Convierte un usuario en artista |
| [**artistPublicArtistUserNameGet**](ArtistasApi.md#artistPublicArtistUserNameGet) | **GET** /artist/public/{artistUserName} | Recupera la información pública de un artista a partir de su nombre de usuario. |
| [**artistPublicGet**](ArtistasApi.md#artistPublicGet) | **GET** /artist/public | Recupera una página de artistas filtrados |
| [**artistPublicTrendingGet**](ArtistasApi.md#artistPublicTrendingGet) | **GET** /artist/public/trending | Recupera una lista de los artistas en tendencia |


<a id="artistDelete"></a>
# **artistDelete**
> SuccessfulResponseDTO artistDelete()

Realiza un borrado lógico de un artista autenticado

Realiza un borrado lógico de un artista autenticado junto con toda su información.El artista y sus canciones/álbumes aparecen borrados pero no se han eliminado de manera física.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    try {
      SuccessfulResponseDTO result = apiInstance.artistDelete();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistDelete");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SuccessfulResponseDTO**](SuccessfulResponseDTO.md)

### Authorization

[BearerAuth](../README.md#BearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Artista borrado de forma existosa |  -  |
| **400** | Mala petición - Artista ya borrado o operación inválida |  -  |
| **401** | Sin autorizacion - No tienes permisos para borrar a un artista |  -  |

<a id="artistIdRestauracionPatch"></a>
# **artistIdRestauracionPatch**
> SuccessfulResponseDTO artistIdRestauracionPatch(id)

Restaura a un artista previamente eliminado de forma lógica

Restaura a un artista previamente eliminado de forma lógica. Requiere que el usuario autenticado sea el dueño de la cuenta de artista.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    BigDecimal id = new BigDecimal("4"); // BigDecimal | 
    try {
      SuccessfulResponseDTO result = apiInstance.artistIdRestauracionPatch(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistIdRestauracionPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **BigDecimal**|  | |

### Return type

[**SuccessfulResponseDTO**](SuccessfulResponseDTO.md)

### Authorization

[BearerAuth](../README.md#BearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Artista restaurado correctamente |  -  |
| **400** | El artista no está eliminado |  -  |
| **401** | No está permitido que restaures a un artista |  -  |

<a id="artistPatch"></a>
# **artistPatch**
> SuccessfulResponseDTO artistPatch(updateArtistDTO)

Actualiza los datos de un artista autenticado

Permite que un artista autenticado actualice su información.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    UpdateArtistDTO updateArtistDTO = new UpdateArtistDTO(); // UpdateArtistDTO | 
    try {
      SuccessfulResponseDTO result = apiInstance.artistPatch(updateArtistDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistPatch");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **updateArtistDTO** | [**UpdateArtistDTO**](UpdateArtistDTO.md)|  | |

### Return type

[**SuccessfulResponseDTO**](SuccessfulResponseDTO.md)

### Authorization

[BearerAuth](../README.md#BearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Artista actualizado correctamente |  -  |
| **401** | No tienes permisos para actualizar a un artista |  -  |

<a id="artistPaymentInfoGet"></a>
# **artistPaymentInfoGet**
> ArtistPayementInfoDTO artistPaymentInfoGet()

Obtiene toda la información financiera de un artista autenticado.

Devuelve los balances, pagos y otra información financiera del artista autenticado. Requiere autenticación.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    try {
      ArtistPayementInfoDTO result = apiInstance.artistPaymentInfoGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistPaymentInfoGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**ArtistPayementInfoDTO**](ArtistPayementInfoDTO.md)

### Authorization

[BearerAuth](../README.md#BearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Información financiera del artista recuperada con éxito |  -  |
| **401** | No tienes permisos para obtener la información financiera del artista |  -  |

<a id="artistPost"></a>
# **artistPost**
> SuccessfulResponseDTO artistPost(artistFormDTO)

Convierte un usuario en artista

Convierte un usuario autentificado en un artista con datos incluidos. Si el usuario ya está registrado como artista se manejara un error de contenido.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    ArtistFormDTO artistFormDTO = new ArtistFormDTO(); // ArtistFormDTO | 
    try {
      SuccessfulResponseDTO result = apiInstance.artistPost(artistFormDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **artistFormDTO** | [**ArtistFormDTO**](ArtistFormDTO.md)|  | |

### Return type

[**SuccessfulResponseDTO**](SuccessfulResponseDTO.md)

### Authorization

[BearerAuth](../README.md#BearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | El usuario ha sido existosamente convertido a artista |  -  |
| **401** | Sin autorizacion - No tienes permisos para convertir un usuario a artista |  -  |

<a id="artistPublicArtistUserNameGet"></a>
# **artistPublicArtistUserNameGet**
> SentArtistDTO artistPublicArtistUserNameGet(artistUserName)

Recupera la información pública de un artista a partir de su nombre de usuario.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    String artistUserName = "artistUserName_example"; // String | Nombre de usuario del artista.
    try {
      SentArtistDTO result = apiInstance.artistPublicArtistUserNameGet(artistUserName);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistPublicArtistUserNameGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **artistUserName** | **String**| Nombre de usuario del artista. | |

### Return type

[**SentArtistDTO**](SentArtistDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Informacion pública del artista recuperada con éxito |  -  |
| **404** | Artista {artistUserName} no encontrado |  -  |

<a id="artistPublicGet"></a>
# **artistPublicGet**
> PageSentArtistDTO artistPublicGet(name, genres, page, size)

Recupera una página de artistas filtrados

Recupera una página de artistas filtrados opcionalmente por nombre y/o género musical. Soporta paginación mediante los parámetros &#x60;page&#x60; y &#x60;size&#x60;.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    String name = "adam"; // String | Nombre parcial del artista para filtrar.
    List<GenreType> genres = Arrays.asList(); // List<GenreType> | Conjunto de géneros para filtrar artistas
    Integer page = 0; // Integer | Número de páginas solicitadas (empezando en 0)
    Integer size = 10; // Integer | Tamaño de la página
    try {
      PageSentArtistDTO result = apiInstance.artistPublicGet(name, genres, page, size);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistPublicGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **name** | **String**| Nombre parcial del artista para filtrar. | [optional] |
| **genres** | [**List&lt;GenreType&gt;**](GenreType.md)| Conjunto de géneros para filtrar artistas | [optional] |
| **page** | **Integer**| Número de páginas solicitadas (empezando en 0) | [optional] [default to 0] |
| **size** | **Integer**| Tamaño de la página | [optional] [default to 10] |

### Return type

[**PageSentArtistDTO**](PageSentArtistDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Página de artistas coincidentes recuperadas con éxito |  -  |
| **404** | No hay artistas que coinciden con la selección de búsqueda |  -  |

<a id="artistPublicTrendingGet"></a>
# **artistPublicTrendingGet**
> List&lt;SentArtistDTO&gt; artistPublicTrendingGet()

Recupera una lista de los artistas en tendencia

Devuelve una lista de los artistas más populares marcados como \&quot;trending\&quot;.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.ArtistasApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    ArtistasApi apiInstance = new ArtistasApi(defaultClient);
    try {
      List<SentArtistDTO> result = apiInstance.artistPublicTrendingGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling ArtistasApi#artistPublicTrendingGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**List&lt;SentArtistDTO&gt;**](SentArtistDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Lista de artistas en tendencia recuperada con éxito |  -  |
| **404** | No existe por el momento ningún artista en tendencia |  -  |

