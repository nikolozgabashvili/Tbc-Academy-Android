package com.example.tbcacademyhomework.domain.core.util


/**
 * Represents different types of network errors that can occur during network operations.
 */
sealed interface NetworkError : Error {
    /**
     * @property message The error message describing the issue.
     */
    data class TextError(val message: String?) : NetworkError

    /**
     * Represents specific types of network errors with predefined meanings.
     */
    enum class ErrorType : NetworkError {
        /** Indicates a network connectivity error (e.g., no internet connection). */
        CONNECTION_ERROR,

        /** Indicates that host can not be reached*/
        UNKNOWN_HOST,

        /** Indicates a request timeout error */
        REQUEST_TIMEOUT,

        /** Indicates a Serialization error*/
        SERIALIZATION_ERROR,

        /** Indicates an error that doesn't fall into other categories. */
        UNKNOWN_ERROR
    }
}