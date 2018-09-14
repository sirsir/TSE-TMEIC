Imports Microsoft.VisualStudio.TestTools.UnitTesting

Imports MESPdLib
Imports MESPdLib.Service

'''<summary>
'''ProductServiceTest のテスト クラスです。すべての
'''ProductServiceTest 単体テストをここに含めます
'''</summary>
<TestClass()> _
Public Class ProductServiceTest


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
    '''GetSerialNoInfo のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub GetSerialNoInfoTest()

        Dim actual As SerialNoInfo = ProductService.GetSerialNoInfo("1505260001", 1, "")
        Assert.AreEqual("XXXX1", actual.Materials(0).MaterialId)
        Assert.AreEqual("SPEC001", actual.SpecProducts(0).SpecId)

    End Sub
End Class
