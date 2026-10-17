package com.tatf.adminces.suite;

import com.tatf.adminces.modules.usuarios.test.AdminCESTest;
import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("AdminCES - Gestión de Testers")
@SelectClasses(AdminCESTest.class)
@IncludeTags("usuarios")
@IncludeEngines("junit-jupiter")
public class SuiteUsuariosTest {
}
