import java.net.*;
import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

class CartItem extends JPanel implements MouseListener {
    CartItem(int quantity, String item, String drink) {
        // Panel
        setBackground(Color.YELLOW);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createDashedBorder(null, 3, 3),
                new EmptyBorder(10, 10, 10, 10)));
        setLayout(new GridLayout(2, 2));
        // Element
        JLabel quantity_label = new JLabel(quantity + " X ");
        quantity_label.setHorizontalAlignment(SwingConstants.CENTER);
        add(quantity_label);
        JLabel item_label = new JLabel(item + " ");
        item_label.setHorizontalAlignment(SwingConstants.LEFT);
        add(item_label);
        JLabel temp = new JLabel();
        add(temp);
        JLabel drink_label = new JLabel(drink);
        drink_label.setHorizontalAlignment(SwingConstants.LEFT);
        add(drink_label);
        addMouseListener(this);
    }

    CartItem() {
        // Panel
        setBackground(Color.WHITE);
        // Element
        JLabel empty = new JLabel("No Orders yet");
        empty.setFont(new Font("Calibri", Font.PLAIN, 20));
        add(empty);
    }

    public void mouseClicked(MouseEvent event) {
        // Do something here
        JOptionPane.showMessageDialog(null, "Hello");
    }

    // Must to make these functions
    public void mousePressed(MouseEvent event) {
    }

    public void mouseEntered(MouseEvent event) {
    }

    public void mouseReleased(MouseEvent event) {
    }

    public void mouseExited(MouseEvent event) {
    }
}

class CartDialog extends JPanel {
    boolean item_added = false;
    ArrayList<String> cart_items = new ArrayList<String>();

    CartDialog() {
        // Panel Setup
        setBackground(Color.WHITE);
        // Elements
        add(new CartItem());
    }

    public void addItem(int quantity, String item, String drink) {
        if (!item_added) {
            removeAll();
            item_added = true;
        }
        CartItem cart_item = new CartItem(quantity, item, drink);
        cart_items.add(Integer.toString(quantity));
        cart_items.add(item);
        cart_items.add(drink);
        add(cart_item);
        revalidate();
        repaint();
    }

    public ArrayList<String> getItems() {
        return cart_items;
    }
}

class Chef extends JFrame implements Runnable {
    CartDialog content;

    Chef() {
        // JFrame setup
        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // Content
        content = new CartDialog();
        add(content);
        // getting the data
        String[] items_data = getItem();
        String[] drink_data = getDrink();
        String[] data = getOrder();
        for (int i = 0; i < data.length; i += 4) {
            int item_index = Arrays.asList(items_data).indexOf(data[i + 2]);
            int drink_index = Arrays.asList(drink_data).indexOf(data[i + 3]);
            content.addItem(Integer.parseInt(data[i + 1]), items_data[item_index + 1], drink_data[drink_index + 1]);
        }
    }

    public static String[] getOrder() {
        // Creating a list
        ArrayList<String> item_list = new ArrayList<String>();
        try {
            // Creating the client
            HttpClient client = HttpClient.newHttpClient();
            // Creating the request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/api/order/")).build();
            // Sending a synchronus request to the server
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            // Getting the json data
            JSONObject obj = new JSONObject("{data:" + response.body() + "}");
            // Getting the banner's data
            JSONArray array = obj.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                // Adding the items in the global list
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("id")));
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("quantity")));
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("item")));
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("drink")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Creating the array
        String[] item_array = new String[item_list.size()];
        item_array = item_list.toArray(item_array);
        // Print message, work done
        System.out.println("Order data fetched...OK");
        // returning the array
        return item_array;
    }

    public static String[] getItem() {
        // Creating a list
        ArrayList<String> item_list = new ArrayList<String>();
        try {
            // Creating the client
            HttpClient client = HttpClient.newHttpClient();
            // Creating the request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/api/item/")).build();
            // Sending a synchronus request to the server
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            // Getting the json data
            JSONObject obj = new JSONObject("{data:" + response.body() + "}");
            // Getting the banner's data
            JSONArray array = obj.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                // Adding the items in the global list
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("id")));
                item_list.add(array.getJSONObject(i).getString("name"));
                item_list.add(array.getJSONObject(i).getString("image"));
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Creating the array
        String[] item_array = new String[item_list.size()];
        item_array = item_list.toArray(item_array);
        // Print message, work done
        System.out.println("Item data fetched...OK");
        // returning the array
        return item_array;
    }

    public static String[] getDrink() {
        // Creating a list
        ArrayList<String> item_list = new ArrayList<String>();
        try {
            // Creating the client
            HttpClient client = HttpClient.newHttpClient();
            // Creating the request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/api/drink/")).build();
            // Sending a synchronus request to the server
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            // Getting the json data
            JSONObject obj = new JSONObject("{data:" + response.body() + "}");
            // Getting the banner's data
            JSONArray array = obj.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                // Adding the items in the global list
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("id")));
                item_list.add(array.getJSONObject(i).getString("name"));
                item_list.add(array.getJSONObject(i).getString("image"));
                item_list.add(Integer.toString(array.getJSONObject(i).getInt("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Creating the array
        String[] item_array = new String[item_list.size()];
        item_array = item_list.toArray(item_array);
        // Print message, work done
        System.out.println("Drink data fetched...OK");
        // returning the array
        return item_array;
    }

    public void run() {
    }

    public static void main(String args[]) {
        new Chef();
    }
}