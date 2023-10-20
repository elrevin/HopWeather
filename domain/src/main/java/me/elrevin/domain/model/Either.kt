package me.elrevin.domain.model

sealed class Either<out T> {
    // Operation was successful
    class Success<T>(internal val value: T): Either<T>()

    // Failure but not exception, for example - server returns "No location found matching parameter 'q'"
    class Failure<T>(internal val msg: String): Either<T>()

    // All is lost
    class Exception<T>(internal val e: Throwable): Either<T>()

    // We are waiting some data from server
    class Loading<T>(internal val value: T?) : Either<T>()

    companion object {
        fun <T>success(value: T): Either<T> = Success(value)
        fun success(): Either<Unit> = Success(Unit)

        fun <T>failure(msg: String): Either<T> = Failure(msg)

        fun <T>exception(e: Throwable): Either<T> = Exception(e)

        fun <T>loading(value: T? = null): Either<T> = Loading(value)

        /**
         * Only for not successful. It can be used when failure or exception happen or loading in progress,
         * but you need Either with another value type
         */
        fun <T>fromEither(another: Either<Any?>): Either<T> = when {
            another.isFailure() -> Failure(another.getFailureMsgOrNull()!!)
            another.isException() -> Exception(another.getThrowableOrNull()!!)
            another.isLoading() -> Loading(null)
            else -> throw kotlin.Exception("Either.fromEither can used only for not successful")
        }
    }

    fun isSuccess() = this is Success
    fun isFailure() = this is Failure
    fun isException() = this is Exception
    fun isLoading() = this is Loading

    fun getValueOrNull(): T? = when {
        isSuccess() -> (this as Success).value
        isLoading() -> (this as Loading).value
        else -> null
    }

    fun getValue(): T = if (isSuccess()) (this as Success).value else throw kotlin.Exception("Either.getValue can used only for successful case")

    fun getFailureMsgOrNull(): String? = if (isFailure()) (this as Failure).msg else null

    fun getThrowableOrNull(): Throwable? = if (isException()) (this as Exception).e else null
}