package com.tatf.adminces.suite;

import com.tatf.adminces.modules.usuarios.test.AdminCESTest;
import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("AdminCES - Accesos y contraseñas")
@SelectClasses(AdminCESTest.class)
@IncludeTags("accesos")
@IncludeEngines("junit-jupiter")
public class SuiteAccesosTest {
}
