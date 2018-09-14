Imports MESPdLib

Imports Microsoft.VisualStudio.TestTools.UnitTesting

Imports MESPdLib.Service



'''<summary>
'''ProcessServiceTest のテスト クラスです。すべての
'''ProcessServiceTest 単体テストをここに含めます
'''</summary>
<TestClass()> _
Public Class ProcessServiceTest


    Private testContextInstance As TestContext

    '''<summary>
    '''現在のテストの実行についての情報および機能を
    '''提供するテスト コンテキストを取得または設定します。
    '''</summary>
    Public Property TestContext() As TestContext
        Get
            Return testContextInstance
        End Get
        Set(ByVal value As TestContext)
            testContextInstance = Value
        End Set
    End Property

#Region "追加のテスト属性"
    '
    'テストを作成するときに、次の追加属性を使用することができます:
    '
    'クラスの最初のテストを実行する前にコードを実行するには、ClassInitialize を使用
    '<ClassInitialize()>  _
    'Public Shared Sub MyClassInitialize(ByVal testContext As TestContext)
    'End Sub
    '
    'クラスのすべてのテストを実行した後にコードを実行するには、ClassCleanup を使用
    '<ClassCleanup()>  _
    'Public Shared Sub MyClassCleanup()
    'End Sub
    '
    '各テストを実行する前にコードを実行するには、TestInitialize を使用
    '<TestInitialize()>  _
    'Public Sub MyTestInitialize()
    'End Sub
    '
    '各テストを実行した後にコードを実行するには、TestCleanup を使用
    '<TestCleanup()>  _
    'Public Sub MyTestCleanup()
    'End Sub
    '
#End Region


    '''<summary>
    '''getProcessInfo のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub getProcessInfoTest()
        Dim actual As ProcessInfo = ProcessService.GetProcessInfo("1505130003", 1)
        Assert.AreEqual("製品名３", actual.ProductName)
    End Sub

    '''<summary>
    '''getProcessInfoByBarcodeTest のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub getProcessInfoByBarcodeTest()
        Dim actual As ProcessInfo = ProcessService.GetProcessInfoByBarcode("")
        Assert.AreEqual("製品名３", actual.ProductName)
    End Sub

End Class
