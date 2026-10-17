package com.tatf.adminces.suite;

import com.tatf.adminces.modules.usuarios.test.AdminCESTest;
import org.junit.platform.suite.api.*;

@Suite
@SuiteDisplayName("AdminCES - Regresión completa")
@SelectClasses(AdminCESTest.class)
@IncludeTags("regresion")
@IncludeEngines("junit-jupiter")
public class SuiteRegresionTest {
}
