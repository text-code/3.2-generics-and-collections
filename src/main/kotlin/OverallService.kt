interface OverallService<E> {
    fun add(entity: E): Long
    fun delete(id: Long)
    fun edit(entity: E)
    fun read(): List<E>
    fun getById(id: Long): E
}