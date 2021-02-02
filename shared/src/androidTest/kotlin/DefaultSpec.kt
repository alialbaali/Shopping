import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.StringSpec
import org.koin.core.module.Module
import org.koin.test.KoinTest

abstract class DefaultSpec(private vararg val modules: Module = emptyArray()) : StringSpec(), KoinTest {

    override fun listeners(): List<TestListener> = if (modules.isNotEmpty()) listOf(KoinTestListener(*modules)) else emptyList()

}
