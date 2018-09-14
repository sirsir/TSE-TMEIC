Imports MESPdLib

Imports Microsoft.VisualStudio.TestTools.UnitTesting

Imports MESPdLib.Service



'''<summary>
'''UserServiceTest のテスト クラスです。すべての
'''UserServiceTest 単体テストをここに含めます
'''</summary>
<TestClass()> _
Public Class UserServiceTest


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
    '''getUserInfo のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub getUserInfoTest()
        Dim actual As UserInfo = UserService.GetUserInfo("2", "2")
        Assert.AreEqual("2", actual.UserName)
    End Sub

    '''<summary>
    '''isAuth のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub isAuthTest()
        Dim actual As Boolean = UserService.IsAuth("2", "2")
        Assert.AreEqual(True, actual)
    End Sub
End Class
