<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>

<div style="width: 200px; vertical-align: bottom;">
  <div class="ganttview-color-table">
    <table>
      <tr>
        <td>
          <div class="ganttview-block ganttview-blue"><bean:message key="plan"/></div>
        </td>
        <td>
          <div class="ganttview-block ganttview-red"><bean:message key="result"/></div>
        </td>
        <td>
          <div class="ganttview-block ganttview-orange"><bean:message key="result.time"/></div>
        </td>
      </tr>
    </table>
  </div>
</div>