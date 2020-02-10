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

class ImageIconButton extends JButton {
    ImageIconButton(String image_address, String text, int width, int height, ActionListener pointer) {
        try {
            setIcon(new ImageIcon(new ImageIcon(new URL(image_address)).getImage()));
            setText(text);
            setHorizontalTextPosition(SwingConstants.CENTER);
            setVerticalTextPosition(SwingConstants.BOTTOM);
            setBackground(Color.WHITE);
            setBorder(new EmptyBorder(10, 5, 10, 5));
            setSize(width, height);
            addActionListener(pointer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ImageIconButton(String image_address) {
        try {
            setIcon(new ImageIcon(new ImageIcon(new URL(image_address)).getImage()));
            setBackground(Color.WHITE);
            setBorder(new EmptyBorder(0, 0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MenuOption extends JPanel implements ActionListener {
    String[] options, drink_options;
    Cart panel_pointer;

    MenuOption(Cart pointer, String[] menu_options, String[] drink_options) {
        options = menu_options;
        this.drink_options = drink_options;
        panel_pointer = pointer;
        // setting the color
        setBackground(Color.WHITE);
        // Buttons
        for (int i = 0; i < options.length; i += 3) {
            add(new ImageIconButton(options[i + 1], options[i], 100, 120, this));
        }
    }

    public void actionPerformed(ActionEvent event) {
        // Setting up new panel
        removeAll();
        setLayout(new GridLayout(1, 1));
        ActionListener pointer = this;
        // Add the item id
        JPanel side_item = new AddSideItem(drink_options, options, new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Setting up new panel
                removeAll();
                setLayout(new FlowLayout());
                // Calling the adding function
                for (int i = 0; i < options.length; i += 3) {
                    add(new ImageIconButton(options[i + 1], options[i], 100, 120, pointer));
                }
                // Reprinting everthing
                revalidate();
                repaint();
            }
        }, panel_pointer, event.getActionCommand());
        add(side_item);
        // Reprinting everthing
        revalidate();
        repaint();
    }
}

class ItemBaseDescription extends JPanel {
    JLabel title, sub_title, price;
    Font heading_font_1, heading_font_2;

    ItemBaseDescription(String item_name, String item_price) {
        // Panel
        setBackground(Color.WHITE);
        setBounds(0, 0, 500, 200);
        setLayout(null);
        // Font
        heading_font_1 = new Font("Calibri", Font.PLAIN, 24);
        heading_font_2 = new Font("Calibri", Font.BOLD, 24);
        // Heading
        title = new JLabel("MAKE YOUR:");
        title.setBounds(50, 20, 300, 30);
        title.setFont(heading_font_1);
        price = new JLabel("\u20B9 " + item_price);
        price.setBounds(370, 20, 100, 30);
        price.setFont(heading_font_2);
        sub_title = new JLabel("BEST OF " + item_name);
        sub_title.setBounds(50, 50, 300, 30);
        sub_title.setFont(heading_font_2);
        add(title);
        add(price);
        add(sub_title);
    }
}

class ItemDescription extends JPanel {
    Font heading_font_1;

    ItemDescription(String item_name, String item_image) {
        // Panel
        setBackground(Color.WHITE);
        setBounds(0, 0, 500, 100);
        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // Font
        heading_font_1 = new Font("Calibri", Font.PLAIN, 24);
        // Elements
        try {
            JButton image = new JButton(new ImageIcon(new ImageIcon(new URL(item_image)).getImage()));
            image.setBounds(10, 15, 30, 30);
            add(image);
            JLabel name = new JLabel(item_name);
            name.setBounds(60, 20, 200, 30);
            name.setFont(heading_font_1);
            add(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ChooseDrink extends JPanel implements ActionListener {
    Cart panel_pointer;
    JButton button_pointer;
    String option_choosed;

    ChooseDrink(Cart pointer, JButton cancel, String option, String[] drink_options) {
        panel_pointer = pointer;
        button_pointer = cancel;
        option_choosed = option;
        // Panel
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // Elements
        // Buttons
        for (int i = 0; i < drink_options.length; i += 3) {
            add(new ImageIconButton(drink_options[i + 1], drink_options[i], 100, 120, this));
        }

    }

    public void actionPerformed(ActionEvent event) {
        // Do something here!!
        panel_pointer.addItem(1, option_choosed, event.getActionCommand());
        button_pointer.doClick();
    }
}

class AddSideItem extends JPanel {

    JButton cancel_button;

    AddSideItem(String[] drink_options, String[] menu_options, ActionListener function, Cart pointer, String item) {
        String item_name = item;
        int item_index = Arrays.asList(menu_options).indexOf(item);
        System.out.println(item_index + " " + menu_options[item_index + 2] + " " + menu_options[item_index + 1] + " "
                + menu_options[item_index]);
        String item_price = menu_options[item_index + 2];
        String item_image = menu_options[item_index + 1];
        // Panel
        setBackground(Color.WHITE);
        setLayout(null);
        // Heading
        JPanel heading = new ItemBaseDescription(item_name, item_price);
        heading.setBounds(0, 0, 500, 100);
        add(heading);
        // Divider
        JLabel divider = new JLabel();
        divider.setOpaque(true);
        divider.setBackground(Color.BLACK);
        divider.setBounds(50, 100, 375, 2);
        // Basic Info
        JPanel info = new ItemDescription(item_name, item_image);
        info.setBounds(50, 110, 375, 60);
        // Buttons
        cancel_button = new JButton("Cancel");
        cancel_button.addActionListener(function);
        cancel_button.setBounds(50, 500, 375, 30);
        // Choose Drink
        JPanel drink = new ChooseDrink(pointer, cancel_button, item, drink_options);
        drink.setBounds(50, 180, 375, 300);
        // Adding the elements to the panel
        add(divider);
        add(info);
        add(drink);
        add(cancel_button);
    }
}

class CartDescription extends JPanel {
    JLabel heading, price;
    Font heading_font_1;
    int amount;

    public void init() {
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2));
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 0, 0, 0)));
        // Elements
        heading_font_1 = new Font("Calibri", Font.PLAIN, 24);
        heading = new JLabel("YOUR ORDER");
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(heading_font_1);
        add(heading);
        price = new JLabel("TOTAL \u20B9" + amount);
        price.setHorizontalAlignment(SwingConstants.CENTER);
        price.setFont(heading_font_1);
        add(price);
    }

    CartDescription(int amount) {
        this.amount = amount;
        init();
    }

    public void changeAmount(int changed_price) {
        removeAll();
        amount = changed_price;
        init();
        revalidate();
        repaint();
    }
}

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
        JLabel empty = new JLabel("Add something here");
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
    }

    public ArrayList<String> getItems() {
        return cart_items;
    }
}

class Cart extends JPanel implements ActionListener {
    CartDescription cart_description;
    JPanel buttons;
    CartDialog content;
    String[] menu_options, drink_options;
    int amount = 0;

    Cart(String[] menu_options, String[] drink_options) {
        this.menu_options = menu_options;
        this.drink_options = drink_options;
        // Panel setup
        setLayout(new GridLayout(3, 1));
        // Heading
        cart_description = new CartDescription(amount);
        add(cart_description);
        // Content
        content = new CartDialog();
        add(content);
        // Buttons
        buttons = new CartButton(this);
        add(buttons);
    }

    public void addItem(int quantity, String item, String drink) {
        // Getting the index
        int item_index = Arrays.asList(menu_options).indexOf(item);
        int drink_index = Arrays.asList(drink_options).indexOf(drink);
        // Saving the amount;
        amount += Integer.parseInt(menu_options[item_index + 2]);
        amount += Integer.parseInt(drink_options[drink_index + 2]);
        System.out.println(amount);
        // adding the item in the cart
        content.addItem(quantity, item, drink);
        // repainting the canvas
        cart_description.changeAmount(amount);
    }

    public void actionPerformed(ActionEvent event) {
        ArrayList<String> items_list = new ArrayList<String>();
        for (String item : content.getItems()) {
            items_list.add(item);
        }
        String[] items_array = new String[items_list.size()];
        items_array = items_list.toArray(items_array);
        // Add the order validation
        // POST data
        postData(items_array);
    }

    public void postData(String[] data) {
        try {
            String[] option_item_array = Project.getItem(true);
            String[] option_drink_array = Project.getDrink(true);
            // Creating the client
            HttpClient client = HttpClient.newHttpClient();
            for (int i = 0; i < data.length; i += 3) {
                // Getting the index
                int item_index = Arrays.asList(option_item_array).indexOf(data[i + 1]);
                int drink_index = Arrays.asList(option_drink_array).indexOf(data[i + 2]);
                System.out.println(item_index + " " + drink_index + " " + data[i + 1] + " " + data[i + 2]);
                // Creating the request
                String body = "{\"quantity\":" + data[i] + ", \"item\":" + option_item_array[item_index + 1]
                        + ", \"drink\":" + option_drink_array[drink_index + 1] + "}";
                System.out.println(body);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8000/api/order/create/"))
                        .headers("Content-Type", "application/json", "Accept", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(body)).build();
                // Sending a synchronus request to the server
                HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
                System.out.println(response.body());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class CartButton extends JPanel {
    JButton cancel_button, validate_button;

    CartButton(ActionListener pointer) {
        // Panel
        setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 2));
        // Cancel Buttons
        cancel_button = new JButton("Cancel Order");
        cancel_button.addActionListener(new CloseListener());
        add(cancel_button);
        // Validate Button
        validate_button = new JButton("Validate Order");
        validate_button.addActionListener(pointer);
        add(validate_button);
    }

    private class CloseListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }
}

class Banner extends JPanel {
    String item_list[];
    int count = 0;

    Banner(String item_list[]) {
        this.item_list = item_list;
        // Panel setup
        // setting the color
        setBackground(Color.WHITE);
        // Buttons
        add(new ImageIconButton(item_list[count]));
        count++;
        // Changing the banner
        changingBanner();
    }

    void changingBanner() {
        // Threading for changing
        Runnable run = new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // Sleeping for 5 seconds
                        Thread.sleep(5000);
                        // removing components and adding new ones
                        removeAll();
                        add(new ImageIconButton(item_list[count]));
                        // revalidating and painting it
                        revalidate();
                        repaint();
                        // Checking if we get at the end of the list
                        if (count == item_list.length - 1)
                            count = 0;
                        else
                            count++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(run).start();
    }
}

class Project extends JFrame {

    JPanel banner, menu_option;
    static int WIDTH = 500, HEIGHT = 1000;

    Project() {
        // Frame setup
        setTitle("Fast Food Order");
        setVisible(true);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // setting the color
        getContentPane().setBackground(Color.WHITE);
        // Top banner
        // Setting up the list
        String[] banner_item_array = this.getBanner();
        // Converting the list to array
        banner = new Banner(banner_item_array);
        // Setting up the list
        String[] option_item_array = Project.getItem(false);
        String[] option_drink_array = Project.getDrink(false);
        // Bottom cart
        Cart cart = new Cart(option_item_array, option_drink_array);
        // Menu options
        menu_option = new MenuOption(cart, option_item_array, option_drink_array);
        // Adding stuff to frame
        add(banner, BorderLayout.NORTH);
        add(menu_option, BorderLayout.CENTER);
        add(cart, BorderLayout.SOUTH);
    }

    public String[] getBanner() {
        // Creating a list
        ArrayList<String> item_list = new ArrayList<String>();
        try {
            // Creating the client
            HttpClient client = HttpClient.newHttpClient();
            // Creating the request
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8000/api/banner/")).build();
            // Sending a synchronus request to the server
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            // Getting the json data
            JSONObject obj = new JSONObject("{data:" + response.body() + "}");
            // Getting the banner's data
            JSONArray array = obj.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                // Adding the items in the global list
                item_list.add(array.getJSONObject(i).getString("image"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Creating the array
        String[] item_array = new String[item_list.size()];
        item_array = item_list.toArray(item_array);
        // Print message, work done
        System.out.println("Banner data fetched...OK");
        // returning the array
        return item_array;
    }

    public static String[] getItem(boolean id_data) {
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
                item_list.add(array.getJSONObject(i).getString("name"));
                if (id_data) {
                    item_list.add(Integer.toString(array.getJSONObject(i).getInt("id")));
                } else {
                    item_list.add(array.getJSONObject(i).getString("image"));
                    item_list.add(Integer.toString(array.getJSONObject(i).getInt("price")));
                }
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

    public static String[] getDrink(boolean id_data) {
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
                item_list.add(array.getJSONObject(i).getString("name"));
                if (id_data) {
                    item_list.add(Integer.toString(array.getJSONObject(i).getInt("id")));
                } else {
                    item_list.add(array.getJSONObject(i).getString("image"));
                    item_list.add(Integer.toString(array.getJSONObject(i).getInt("price")));
                }
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

    public static void main(String args[]) {
        new Project();
    }
}