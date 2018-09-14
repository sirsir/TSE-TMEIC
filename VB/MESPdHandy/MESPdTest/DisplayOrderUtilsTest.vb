Imports System.Collections.Generic

Imports Microsoft.VisualStudio.TestTools.UnitTesting

Imports MESPdLib



'''<summary>
'''DisplayOrderUtilsTest のテスト クラスです。すべての
'''DisplayOrderUtilsTest 単体テストをここに含めます
'''</summary>
<TestClass()> _
Public Class DisplayOrderUtilsTest


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
    '''FirstOrder のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub FirstOrderTest()

        Dim expected As List(Of DisplayOrder) = CreateDisplayOrderList()
        Dim actual As DisplayOrder

        actual = DisplayOrderUtils.FirstOrder(expected)
        Assert.AreEqual(1, actual.DisplayOrder)
    End Sub

    '''<summary>
    '''LastOrder のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub LastOrderTest()

        Dim expected As List(Of DisplayOrder) = CreateDisplayOrderList()
        Dim actual As DisplayOrder

        actual = DisplayOrderUtils.LastOrder(expected)
        Assert.AreEqual(3, actual.DisplayOrder)
    End Sub

    '''<summary>
    '''NextOrder のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub NextOrderTest()

        Dim expected As List(Of DisplayOrder) = CreateDisplayOrderList()
        Dim actual As DisplayOrder

        actual = DisplayOrderUtils.NextOrder(expected, 1)
        Assert.AreEqual(2, actual.DisplayOrder)

        actual = DisplayOrderUtils.NextOrder(expected, 2)
        Assert.AreEqual(3, actual.DisplayOrder)

        actual = DisplayOrderUtils.NextOrder(expected, 3)
        Assert.AreEqual(1, actual.DisplayOrder)

        expected = Create1DisplayOrderList()

        actual = DisplayOrderUtils.NextOrder(expected, 1)
        Assert.AreEqual(1, actual.DisplayOrder)
    End Sub

    '''<summary>
    '''PrevOrder のテスト
    '''</summary>
    <TestMethod()> _
    Public Sub PrevOrderTest()

        Dim expected As List(Of DisplayOrder) = CreateDisplayOrderList()
        Dim actual As DisplayOrder

        actual = DisplayOrderUtils.PrevOrder(expected, 3)
        Assert.AreEqual(2, actual.DisplayOrder)

        actual = DisplayOrderUtils.PrevOrder(expected, 2)
        Assert.AreEqual(1, actual.DisplayOrder)

        actual = DisplayOrderUtils.PrevOrder(expected, 1)
        Assert.AreEqual(3, actual.DisplayOrder)

        expected = Create1DisplayOrderList()

        actual = DisplayOrderUtils.PrevOrder(expected, 1)
        Assert.AreEqual(1, actual.DisplayOrder)
    End Sub

    Private Function Create1DisplayOrderList() As List(Of DisplayOrder)

        Dim list As New List(Of DisplayOrder)
        list.Add(New DisplayOrder() With {.DisplayOrder = 1})
        Return list

    End Function

    Private Function CreateDisplayOrderList() As List(Of DisplayOrder)

        Dim list As New List(Of DisplayOrder)
        list.Add(New DisplayOrder() With {.DisplayOrder = 1})
        list.Add(New DisplayOrder() With {.DisplayOrder = 2})
        list.Add(New DisplayOrder() With {.DisplayOrder = 3})

        Return list

    End Function

End Class
