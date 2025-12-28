package com.petadopt.util;

import jakarta.servlet.http.HttpServletRequest;

public class ErrorHandler {

    public static void handleError(
            HttpServletRequest request,
            String message,
            Exception e
    ) {
        request.setAttribute("errorMessage", message);

        if (e != null) {
            e.printStackTrace(); // for debugging/logging
        }
    }
}
