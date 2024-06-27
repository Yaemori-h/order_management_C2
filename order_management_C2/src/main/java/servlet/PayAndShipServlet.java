/* 作成者：八重森
 * 作成日：6/24
 * 
 */

package servlet;

import java.io.IOException;

import bean.OrderItem;
import dao.orderItemDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/payAndShip")
public class PayAndShipServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = "";
        String cmd = "";

        try {
            // 入力データの文字コードの指定
            request.setCharacterEncoding("UTF-8");

            // パラメータの取得
            String orderIdStr = request.getParameter("order_id");
            int orderId = Integer.parseInt(orderIdStr);

            // DAOを使用して受注情報を取得
            orderItemDAO orderItemDao = new orderItemDAO();
            OrderItem orderItem = orderItemDao.selectByOrderId(orderId);

            if (orderItem == null) {
                error = "指定された受注情報が存在しません。";
                cmd = "orderList";
            } else {
                request.setAttribute("orderItem", orderItem);
            }

        } catch (IllegalStateException e) {
            error = "DB接続エラーのため、受注情報を取得できませんでした。";
            cmd = "orderList";
        } catch (NumberFormatException e) {
            error = "受注IDが不正です。";
            cmd = "orderList";
        } finally {
            if (error.equals("")) {
                request.getRequestDispatcher("/view/orderUpdate.jsp").forward(request, response);
            } else {
                request.setAttribute("cmd", cmd);
                request.setAttribute("error", error);
                request.getRequestDispatcher("/view/managerError.jsp").forward(request, response);
            }
        }
    }
}
