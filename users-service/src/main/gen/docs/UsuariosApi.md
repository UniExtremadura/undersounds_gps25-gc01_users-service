# UsuariosApi

All URIs are relative to *http://localhost:8080/api*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**usersDelete**](UsuariosApi.md#usersDelete) | **DELETE** /users | Realiza un borrado lógico de un usuario autenticado |
| [**usersGet**](UsuariosApi.md#usersGet) | **GET** /users | Obtiene la información de los usuarios autenticados |
| [**usersLoginAccesoGet**](UsuariosApi.md#usersLoginAccesoGet) | **GET** /users/login/acceso | Obtiene los usuarios que coincidan con el correo y contraseña |
| [**usersLoginPost**](UsuariosApi.md#usersLoginPost) | **POST** /users/login | Iniciar sesión de un usuario |
| [**usersPatch**](UsuariosApi.md#usersPatch) | **PATCH** /users | Actualiza los datos de un usuario autenticado |
| [**usersPublicIdGet**](UsuariosApi.md#usersPublicIdGet) | **GET** /users/{public-id} | Obtiene los datos del usuario con el id solicitado |
| [**usersRegistroPost**](UsuariosApi.md#usersRegistroPost) | **POST** /users/registro | Registrar un nuevo usuario en el sistema |
| [**usersUsernameGet**](UsuariosApi.md#usersUsernameGet) | **GET** /users/{username} | Obtiene los datos del usuario con el nombre solicitado |


<a id="usersDelete"></a>
# **usersDelete**
> SuccessfulResponseDTO usersDelete()

Realiza un borrado lógico de un usuario autenticado

Realiza un borrado lógico de un usuario autenticado junto con toda su información. El usuario y su información personal aparecen borrados pero no se han eliminado de manera física.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    try {
      SuccessfulResponseDTO result = apiInstance.usersDelete();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersDelete");
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
| **200** | Usuario borrado de forma existosa |  -  |
| **400** | Mala petición - Usuario ya borrado o operación inválida |  -  |
| **401** | Sin autorizacion - No tienes permisos para borrar a un usuario |  -  |

<a id="usersGet"></a>
# **usersGet**
> UserProfileDTO usersGet()

Obtiene la información de los usuarios autenticados

Recupera la información del usuario actualmente autentificado. Requiere un token válido de verificación.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    try {
      UserProfileDTO result = apiInstance.usersGet();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersGet");
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

[**UserProfileDTO**](UserProfileDTO.md)

### Authorization

[BearerAuth](../README.md#BearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Se ha obtenido correctamente la información de los usuarios autenticados |  -  |

<a id="usersLoginAccesoGet"></a>
# **usersLoginAccesoGet**
> UserProfileDTO usersLoginAccesoGet(email, password)

Obtiene los usuarios que coincidan con el correo y contraseña

Consulta para el login del usuario, buscando por su email y contraseña.   Se utiliza para verificar si existe un usuario con esos datos para poder iniciar sesión.  **Nota**: Este endpoint está diseñado para comunicación entre microservicios y requiere de una API key válida. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    String email = "jlosada@gmail.com"; // String | El email del usuario que se quire recuperar
    String password = "jyEfkri45"; // String | La contraseña del usuario que se quiere recuperar
    try {
      UserProfileDTO result = apiInstance.usersLoginAccesoGet(email, password);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersLoginAccesoGet");
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
| **email** | **String**| El email del usuario que se quire recuperar | |
| **password** | **String**| La contraseña del usuario que se quiere recuperar | |

### Return type

[**UserProfileDTO**](UserProfileDTO.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Autenticación existosa. Retorna los datos del usuario |  -  |
| **400** | Parámetros inválidos o faltantes |  -  |
| **401** | Autenticación fallida - Credenciales inválidas o API key no autorizada |  -  |
| **500** | Error interno del servidor |  -  |

<a id="usersLoginPost"></a>
# **usersLoginPost**
> UserProfileDTO usersLoginPost(userLoginDTO)

Iniciar sesión de un usuario

Permite a un usuario iniciar sesión con su nombre de usuario y contraseñan. Devuelve el perfil del usuario si las credenciales son correctas, o un error 404 si no lo son. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    UserLoginDTO userLoginDTO = new UserLoginDTO(); // UserLoginDTO | 
    try {
      UserProfileDTO result = apiInstance.usersLoginPost(userLoginDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersLoginPost");
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
| **userLoginDTO** | [**UserLoginDTO**](UserLoginDTO.md)|  | |

### Return type

[**UserProfileDTO**](UserProfileDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Login correcto, devuelve la información del usuario |  -  |
| **401** | Credenciales incorrectas |  -  |

<a id="usersPatch"></a>
# **usersPatch**
> SuccessfulResponseDTO usersPatch(updateUserDTO)

Actualiza los datos de un usuario autenticado

Permite al usuario autenticado actualizar su información personal.   Devuelve un objeto con el resultado de la operación. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure HTTP bearer authorization: BearerAuth
    HttpBearerAuth BearerAuth = (HttpBearerAuth) defaultClient.getAuthentication("BearerAuth");
    BearerAuth.setBearerToken("BEARER TOKEN");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    UpdateUserDTO updateUserDTO = new UpdateUserDTO(); // UpdateUserDTO | 
    try {
      SuccessfulResponseDTO result = apiInstance.usersPatch(updateUserDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersPatch");
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
| **updateUserDTO** | [**UpdateUserDTO**](UpdateUserDTO.md)|  | |

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
| **200** | Se ha actualizado correctamente al usuario |  -  |
| **500** | Usuario no encontrado |  -  |

<a id="usersPublicIdGet"></a>
# **usersPublicIdGet**
> UserProfileDTO usersPublicIdGet(publicId)

Obtiene los datos del usuario con el id solicitado

Consulta para verificar si ya existe un usuario con el mismo ID. Se utiliza para verificar la existencia de un usuario cuando se busca por su identificador único.  **Nota**: Este endpoint está diseñado para comunicación entre microservicios  y requiere de una API key válida  

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    BigDecimal publicId = new BigDecimal("34"); // BigDecimal | El id del usuario que se quiere consultar
    try {
      UserProfileDTO result = apiInstance.usersPublicIdGet(publicId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersPublicIdGet");
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
| **publicId** | **BigDecimal**| El id del usuario que se quiere consultar | |

### Return type

[**UserProfileDTO**](UserProfileDTO.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Autenticación exitosa. Devuelve los datos del usuario |  -  |
| **400** | Parámetros inválidos o faltantes |  -  |
| **401** | Autenticación fallida - Id inválido o API key no autorizada |  -  |
| **500** | Error interno del servidor |  -  |

<a id="usersRegistroPost"></a>
# **usersRegistroPost**
> UserProfileDTO usersRegistroPost(userRegisterDTO)

Registrar un nuevo usuario en el sistema

Crea un nuevo usuario con los datos proporcionados.   Devuelve un objeto con la información del usuario registrado.   Lanza un error si el correo electrónico o el nombre de usuario ya existen. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    UserRegisterDTO userRegisterDTO = new UserRegisterDTO(); // UserRegisterDTO | 
    try {
      UserProfileDTO result = apiInstance.usersRegistroPost(userRegisterDTO);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersRegistroPost");
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
| **userRegisterDTO** | [**UserRegisterDTO**](UserRegisterDTO.md)|  | |

### Return type

[**UserProfileDTO**](UserProfileDTO.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | El usuario ha sido registrado correctamente en el sistema |  -  |
| **400** | Error en la petición (datos inválidos) |  -  |
| **409** | El correo electrónico o el nombre del usuario ya están registrados |  -  |
| **500** | Internal Server Error |  -  |

<a id="usersUsernameGet"></a>
# **usersUsernameGet**
> UserProfileDTO usersUsernameGet(username)

Obtiene los datos del usuario con el nombre solicitado

Consulta para verificar si ya existe un usuario con el mismo nombre de usuario.   Se utiliza para verificar la existencia de un nombre de usuario duplicado durante el proceso de registro.  **Nota**: Este endpoint está diseñado para comunicación entre microservicios  y requiere de una API key válida. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UsuariosApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost:8080/api");
    
    // Configure API key authorization: ApiKeyAuth
    ApiKeyAuth ApiKeyAuth = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyAuth");
    ApiKeyAuth.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //ApiKeyAuth.setApiKeyPrefix("Token");

    UsuariosApi apiInstance = new UsuariosApi(defaultClient);
    String username = "juanM12"; // String | El nombre de usuario que se desea consultar
    try {
      UserProfileDTO result = apiInstance.usersUsernameGet(username);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UsuariosApi#usersUsernameGet");
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
| **username** | **String**| El nombre de usuario que se desea consultar | |

### Return type

[**UserProfileDTO**](UserProfileDTO.md)

### Authorization

[ApiKeyAuth](../README.md#ApiKeyAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Autenticación exitosa. Devuelve los datos del usuario |  -  |
| **400** | Parámetros inválidos o faltantes |  -  |
| **401** | Autenticación fallida - Username inválido o API key no autorizada |  -  |
| **500** | Error interno del servidor |  -  |

