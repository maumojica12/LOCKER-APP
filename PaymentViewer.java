import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.collections.*;
import java.sql.Timestamp;
import java.util.List;

public class PaymentViewer extends Application {

    private TableView<PaymentManager.Payment> table;

    @Override
    public void start(Stage stage) {
        stage.setTitle("View All Payments");

        // Table columns
        TableColumn<PaymentManager.Payment, Integer> idCol = new TableColumn<>("Payment ID");
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().paymentID).asObject());

        TableColumn<PaymentManager.Payment, String> bookingCol = new TableColumn<>("Booking Reference");
        bookingCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().bookingReference));

        TableColumn<PaymentManager.Payment, Integer> userCol = new TableColumn<>("User ID");
        userCol.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().userID).asObject());

        TableColumn<PaymentManager.Payment, Double> amountCol = new TableColumn<>("Amount (₱)");
        amountCol.setCellValueFactory(data -> new javafx.beans.property.SimpleDoubleProperty(data.getValue().paymentAmount).asObject());

        TableColumn<PaymentManager.Payment, String> methodCol = new TableColumn<>("Method");
        methodCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().paymentMethod));

        TableColumn<PaymentManager.Payment, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().paymentStatus));

        TableColumn<PaymentManager.Payment, Timestamp> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().paymentDate));

        // Create table
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().addAll(idCol, bookingCol, userCol, amountCol, methodCol, statusCol, dateCol);

        // Load data
        refreshTable();

        // Buttons
        Button refreshBtn = new Button("Refresh");
        refreshBtn.setOnAction(e -> refreshTable());

        Button closeBtn = new Button("Return to Main Menu");
        closeBtn.setOnAction(e -> stage.close());

        HBox buttons = new HBox(10, refreshBtn, closeBtn);
        buttons.setStyle("-fx-alignment: center; -fx-padding: 10;");

        VBox layout = new VBox(10, table, buttons);
        layout.setStyle("-fx-padding: 15;");

        stage.setScene(new Scene(layout, 900, 500));
        stage.show();
    }

    private void refreshTable() {
        List<PaymentManager.Payment> paymentList = PaymentManager.viewAllPayments();
        ObservableList<PaymentManager.Payment> data = FXCollections.observableArrayList(paymentList);
        table.setItems(data);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
