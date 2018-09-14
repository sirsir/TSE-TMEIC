<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>

<div style="width: 300px; vertical-align: bottom;">
  <div class="ganttview-color-table" style="width: 323px;">
    <table>
      <tr>
        <td>
          <div class="ganttview-block ganttview-blue" style="width: 100px;"><bean:message key="plan"/></div>
        </td>
        <td>
          <div class="ganttview-block ganttview-red" style="width: 100px;"><bean:message key="result.quantity"/></div>
        </td>
        <td>
          <div class="ganttview-block ganttview-orange" style="width: 100px;"><bean:message key="result.time"/></div>
        </td>
      </tr>
    </table>
  </div>
</div>