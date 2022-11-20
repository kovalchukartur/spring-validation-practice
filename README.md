// https://www.baeldung.com/global-error-handler-in-a-spring-rest-api

// before response
// {
//    "timestamp": "2022-11-20T10:11:13.378+00:00",
//    "status": 400,
//    "error": "Bad Request",
//    "path": "/users/123/tag"
//}

// after response
// {
//    "status": 400,
//    "message": "validation error",
//    "fieldErrors": [
//        {
//            "codes": [
//                "NotBlank.tagDto.name",
//                "NotBlank.name",
//                "NotBlank.java.lang.String",
//                "NotBlank"
//            ],
//            "arguments": [
//                {
//                    "codes": [
//                        "tagDto.name",
//                        "name"
//                    ],
//                    "arguments": null,
//                    "defaultMessage": "name",
//                    "code": "name"
//                }
//            ],
//            "defaultMessage": "must not be blank",
//            "objectName": "tagDto",
//            "field": "name",
//            "rejectedValue": "  ",
//            "bindingFailure": false,
//            "code": "NotBlank"
//        },
//        {
//            "codes": [
//                "NotBlank.tagDto.desc",
//                "NotBlank.desc",
//                "NotBlank.java.lang.String",
//                "NotBlank"
//            ],
//            "arguments": [
//                {
//                    "codes": [
//                        "tagDto.desc",
//                        "desc"
//                    ],
//                    "arguments": null,
//                    "defaultMessage": "desc",
//                    "code": "desc"
//                }
//            ],
//            "defaultMessage": "must not be blank",
//            "objectName": "tagDto",
//            "field": "desc",
//            "rejectedValue": "",
//            "bindingFailure": false,
//            "code": "NotBlank"
//        }
//    ]
//}