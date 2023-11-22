# microservicio

# PASOS PARA REVISAR MICROSERVICIO

1. Realice un clone del repositorio https://github.com/santoluque/microservicio.git

2. En una instalación local de POSTGRESQL ejecute las sentencia SQL que se encuentran en el archivo BaseDatos.sql,
   este archivo creara el usuario de base de datos y las bases para el microservicio crm y bill.
   
   Nota:Las entidades (Tablas) se crearan automaticamente en los esquemas de base de datos correspondientes de crm y bill.

3. Abra los proyectos bill y crm con un IDE de programación para JAVA (Preferible Netbeans)

4. Compile los proyectos bill y crm

5. Ejecute desde línea de comando ingresando al directorio target de cada uno de los proyectos la sentencia:
	5.1. Para CRM ingresar a: microservicio\crm\crm\target
	5.1.1. Ejecutar java -jar crm-0.0.1-SNAPSHOT.jar

	5.2. Para BILL ingresar a: microservicio\bill\bill\target
	5.2.1. Ejecutar java -jar bill-0.0.1-SNAPSHOT.jar

6. Cargue el archivo API-POSTMAN.json en el aplicativo Postman, se cargara el proyecto TEST-GLOBANT con dos folders,
   crm y bill

7. Ejecute el recurso crm-create-cliente para crear un cliente, ejemplo de json de request:
    {
    "contrasena": "5678",
    "estado": true,
    "persona": {
        "identificacion": "0000000002",
        "nombre": "Marianela Montalvo",
        "genero": "F",
        "edad": 30,
        "direccion": "Amazonas y NNUU",
        "telefono": "097548965"
    }
}

8. Ejecute el recurso create-cuenta para crear un cuenta (tomar en cuenta el id del cliente devuelto en el response del paso 7), ejemplo de json de request:

{
    "numeroCuenta": "585545",
    "tipoCuenta": "C",
    "saldoInicial": 1000.00,
    "estado": true,
    "clienteId":12
}

9. Ejecute el recurso create-movimiento para registrar un movimiento (los valores permitidos para el key tipoMovimiento
son CR: para crédito, y DB: para débito), ejemplo de json de request:
Nota: Tomar en cuenta el número de cuenta creado en el punto 8.

{
    "fecha": "2023-11-06 08:35:00",
    "tipoMovimiento": "CR",
    "valor": 150.00,
    "saldo": 0.00,
    "numeroCuenta":"478758"
}

10. Ejecute el recurso reportes para obtener el reporte de movimientos con saldos,ejemplo de consumo; 

http://localhost:8081/bill/v1/reportes/clientes/12?desde=2023-11-06&hasta=2023-11-07

Los parametros del servicio son: parametro de path para id de cliente, parámetros de query desde y hasta para rangos de fecha
de consulta en formato YYYY-MM-DD 

Ejemplo de salida de reporte, devuelve un array con estructura cuenta con maestro de la cuenta y estructura movimientos
que devuelve un array de movimientos de la cuenta

{
    "code": 200,
    "message": "Success.",
    "data": [
        {
            "cuenta": {
                "numeroCuenta": "585545",
                "tipoCuenta": "C",
                "saldoInicial": 1000.00,
                "estado": true,
                "clienteId": 12
            },
            "movimientos": []
        },
        {
            "cuenta": {
                "numeroCuenta": "478758",
                "tipoCuenta": "A",
                "saldoInicial": 200.00,
                "estado": true,
                "clienteId": 12
            },
            "movimientos": [
                {
                    "idMovimiento": 13,
                    "fecha": "2023-11-06 08:20:00",
                    "tipoMovimiento": "DB",
                    "valor": 575.00,
                    "saldo": 1425.00,
                    "numeroCuenta": "478758"
                },
                {
                    "idMovimiento": 16,
                    "fecha": "2023-11-06 08:35:00",
                    "tipoMovimiento": "CR",
                    "valor": 150.00,
                    "saldo": 1585.00,
                    "numeroCuenta": "478758"
                },
                {
                    "idMovimiento": 15,
                    "fecha": "2023-11-06 09:00:00",
                    "tipoMovimiento": "CR",
                    "valor": 10.00,
                    "saldo": 1585.00,
                    "numeroCuenta": "478758"
                }
            ]
        }
    ]
}

 
   
