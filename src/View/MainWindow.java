package View;

import Controller.Database.DatabaseManager;
import Controller.Model.*;
import Controller.Model.Listener.IUpdateListener;
import Controller.Session.SessionManager;
import Model.Enum.UserLevel;
import View.Button.SideButton;
import View.Form.User.LoginForm;
import View.Utility.FormUtilities;
import View.Utility.SpringUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class MainWindow extends JFrame implements IUpdateListener
{
    public final static String BRAND_ID = "Card.Brand.Panel";
    public final static String MODEL_ID = "Card.Model.Panel";
    public final static String VEHICLE_ID = "Card.VehicleRecord.Panel";
    public final static String SALES_ID = "Card.Sales.Panel";
    public final static String USER_ID = "Card.Sales.UserRecord";
    private static final Color BACKGROUND_COLOR = new Color(0x2C394B);
    private final SideButton vehiclesButton = new SideButton("VEHICLES");
    private final SideButton modelsButton = new SideButton("MODELS");
    private final SideButton brandsButton = new SideButton("BRANDS");
    private final SideButton transactionsButton = new SideButton("SALES");
    private final SideButton userButton = new SideButton("USERS");

    private final JTable displayTable = new JTable();
    private final JScrollPane cCenterPanel = new JScrollPane(displayTable);
    private final JPanel cLeftPanel = new JPanel();
    private final JPanel cRightPanel = new JPanel();
    private final JLabel welcomeLabel = new JLabel();

    private final SideButton insertButton = new SideButton("INSERT");
    private final SideButton modifyButton = new SideButton("MODIFY");
    private final SideButton deleteButton = new SideButton("DELETE");
    private final SideButton logInButton = new SideButton("LOG IN");
    private final SideButton logOutButton = new SideButton("LOG OUT");
    private final SideButton registerButton = new SideButton("REGISTER");

    private final DatabaseManager databaseManager;
    private IController crudController;
    private HashMap<String, SideButton> idToButtonMap;
    private HashMap<String, IController> idToCRUDControllerMap;

    public MainWindow(DatabaseManager databaseManager)
    {
        super("JoeCar");

        this.databaseManager = databaseManager;

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(BACKGROUND_COLOR);

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(820, 640);

        cLeftPanel.setBackground(BACKGROUND_COLOR);
        cLeftPanel.setLayout(new SpringLayout());
        cLeftPanel.add(brandsButton);
        cLeftPanel.add(modelsButton);
        cLeftPanel.add(vehiclesButton);
        cLeftPanel.add(transactionsButton);
        cLeftPanel.add(userButton);
        SpringUtilities.makeCompactGrid(cLeftPanel, 5, 1, 6, 6, 6, 6);

        cRightPanel.setBackground(BACKGROUND_COLOR);
        cRightPanel.setLayout(new SpringLayout());
        cRightPanel.add(insertButton);
        cRightPanel.add(modifyButton);
        cRightPanel.add(deleteButton);
        cRightPanel.add(logInButton);
        cRightPanel.add(logOutButton);
        cRightPanel.add(registerButton);
        SpringUtilities.makeCompactGrid(cRightPanel, 6, 1, 6, 6, 6, 6);

        var cellRenderer = new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column)
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("Century Gothic", Font.PLAIN, 14));
                return c;
            }
        };
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        cCenterPanel.getViewport().setBackground(BACKGROUND_COLOR);
        displayTable.setBackground(BACKGROUND_COLOR);
        displayTable.setForeground(SideButton.TEXT_COLOR);
        displayTable.setSelectionBackground(SideButton.SELECTED_COLOR);
        displayTable.setSelectionForeground(SideButton.TEXT_COLOR);
        displayTable.setShowVerticalLines(false);
        displayTable.setDefaultRenderer(Object.class, cellRenderer);
        displayTable.setRowHeight(28);

        var header = displayTable.getTableHeader();
        header.setBackground(BACKGROUND_COLOR);
        header.setForeground(SideButton.TEXT_COLOR);
        var headerRenderer = new DefaultTableCellRenderer()
        {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column)
            {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(SideButton.BUTTON_FONT);
                return c;
            }
        };
        headerRenderer.setBorder(null);
        headerRenderer.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(headerRenderer);
        header.setPreferredSize(new Dimension(0, 48));

        cCenterPanel.setBorder(BorderFactory.createEmptyBorder());

        JLabel mainLabel = new JLabel("JoeCar Inventory System");
        mainLabel.setFont(new Font("Century Gothic", Font.BOLD, 36));
        mainLabel.setForeground(SideButton.TEXT_COLOR);
        mainLabel.setHorizontalAlignment(JLabel.CENTER);

        welcomeLabel.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        welcomeLabel.setForeground(SideButton.TEXT_COLOR);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

        var sepLabel = new JLabel("...");
        sepLabel.setFont(new Font("Century Gothic", Font.PLAIN, 36));
        sepLabel.setVisible(false);

        var closeButton = new SideButton("X");
        closeButton.addActionListener(e -> System.exit(0));
        closeButton.setBorderPainted(false);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setOpaque(false);
        closeButton.setHorizontalAlignment(JLabel.RIGHT);
        closeButton.setPreferredSize(new Dimension(24, 24));

        mainPanel.add(new JLabel());
        mainPanel.add(new JLabel());
        mainPanel.add(closeButton);

        mainPanel.add(new JLabel());
        mainPanel.add(mainLabel);
        mainPanel.add(new JLabel());

        mainPanel.add(new JLabel());
        mainPanel.add(welcomeLabel);
        mainPanel.add(new JLabel());

        mainPanel.add(new JLabel());
        mainPanel.add(sepLabel);
        mainPanel.add(new JLabel());

        mainPanel.add(cLeftPanel);
        mainPanel.add(cCenterPanel);
        mainPanel.add(cRightPanel);

        mainPanel.setLayout(new SpringLayout());
        SpringUtilities.makeCompactGrid(mainPanel, 5, 3, 6, 6, 6, 6);

        setUndecorated(true);

        MouseAdapter frameDragListener = new MouseAdapter()
        {
            private Point mouseDownCompCoords = null;

            @Override
            public void mouseReleased(MouseEvent e) { mouseDownCompCoords = null; }

            public void mousePressed(MouseEvent e) { mouseDownCompCoords = e.getPoint(); }

            public void mouseDragged(MouseEvent e)
            {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        };
        addMouseListener(frameDragListener);
        addMouseMotionListener(frameDragListener);

        mainPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, SideButton.CLICKED_COLOR));

        new Thread(() ->
        {
            double counter = 0;
            while (true)
            {
                try { Thread.sleep(10); }
                catch (InterruptedException e) { e.printStackTrace(); }
                counter += 0.05;
                while (counter > 2.f * Math.PI) { counter -= 2.f * Math.PI; }
                var color = new Color(
                        (int) (FormUtilities.calculateRainbow(counter + 0) * 255.),
                        (int) (FormUtilities.calculateRainbow(counter + 2) * 255.),
                        (int) (FormUtilities.calculateRainbow(counter + 4) * 255.));
                mainPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, color));
            }
        }).start();


        initializeIdToObjectMappings();
        setupSidebarButtons();
        setupCRUDButtons();
        updateMenuState(VEHICLE_ID); // Set the "VehicleRecord" menu as the starting position
    }

    private void highlightButton(JButton targetButton)
    {
        for (SideButton currButton : idToButtonMap.values())
        {
            currButton.setSideButtonSelected(targetButton == currButton);
            currButton.updateAppearance();
        }
    }

    public void updateMenuState(String id)
    {
        JButton button = idToButtonMap.get(id);
        IController controller = idToCRUDControllerMap.get(id);
        if (button != null && controller != null)
        {
            highlightButton(button);
            crudController = controller;
            crudController.loadViewTable();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Invalid ID to Object mapping", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        var user = SessionManager.get().getCurrentUser();

        if (user == null)
        {
            welcomeLabel.setText("You are not logged in, please log in.");

            registerButton.setSideButtonEnabled(true);
            logInButton.setSideButtonEnabled(true);
            logOutButton.setSideButtonEnabled(false);

            vehiclesButton.setSideButtonEnabled(false);
            modelsButton.setSideButtonEnabled(false);
            brandsButton.setSideButtonEnabled(false);
            transactionsButton.setSideButtonEnabled(false);
            userButton.setSideButtonEnabled(false);

            insertButton.setSideButtonEnabled(false);
            modifyButton.setSideButtonEnabled(false);
            deleteButton.setSideButtonEnabled(false);

            cCenterPanel.setVisible(false);
        }
        else
        {
            welcomeLabel.setText(String.format("Welcome, %s! You are logged in as %s.", user.getUserName(), user.getUserLevel().toString()));

            registerButton.setSideButtonEnabled(false);
            logInButton.setSideButtonEnabled(false);
            logOutButton.setSideButtonEnabled(true);

            boolean salesAccess = user.getUserLevel() == UserLevel.ADMIN || user.getUserLevel() == UserLevel.SALES_MANAGER;
            boolean adminAccess = user.getUserLevel() == UserLevel.ADMIN;
            boolean managerAccess = user.getUserLevel() == UserLevel.ADMIN || user.getUserLevel() == UserLevel.PRODUCT_MANAGER;

            vehiclesButton.setSideButtonEnabled(true);
            transactionsButton.setSideButtonEnabled(salesAccess);
            brandsButton.setSideButtonEnabled(managerAccess);
            modelsButton.setSideButtonEnabled(managerAccess);
            userButton.setSideButtonEnabled(adminAccess);

            insertButton.setSideButtonEnabled(true);
            modifyButton.setSideButtonEnabled(true);
            deleteButton.setSideButtonEnabled(true);

            cCenterPanel.setVisible(true);
        }
    }

    private void setupSidebarButtons()
    {
        vehiclesButton.addActionListener(e -> updateMenuState(VEHICLE_ID));
        modelsButton.addActionListener(e -> updateMenuState(MODEL_ID));
        brandsButton.addActionListener(e -> updateMenuState(BRAND_ID));
        transactionsButton.addActionListener(e -> updateMenuState(SALES_ID));
        userButton.addActionListener(e -> updateMenuState(USER_ID));
        registerButton.addActionListener(e -> ((UserController) idToCRUDControllerMap.get(USER_ID)).openRegistrationWindow(MainWindow.this));
    }

    private void setupCRUDButtons()
    {
        insertButton.addActionListener(e -> crudController.openCreateWindow(MainWindow.this));
        modifyButton.addActionListener(e -> crudController.openModifyWindow(MainWindow.this));
        deleteButton.addActionListener(e -> crudController.openDeleteWindow());

        logInButton.addActionListener(e ->
        {
            LoginForm loginForm = new LoginForm(this);
            loginForm.setVisible(true);
        });

        logOutButton.addActionListener(e ->
        {
            SessionManager.get().logOut();
            updateMenuState(VEHICLE_ID);
        });
    }

    private void initializeIdToObjectMappings()
    {
        idToButtonMap = new HashMap<>();
        idToButtonMap.put(VEHICLE_ID, vehiclesButton);
        idToButtonMap.put(MODEL_ID, modelsButton);
        idToButtonMap.put(BRAND_ID, brandsButton);
        idToButtonMap.put(SALES_ID, transactionsButton);
        idToButtonMap.put(USER_ID, userButton);

        idToCRUDControllerMap = new HashMap<>();
        idToCRUDControllerMap.put(VEHICLE_ID, new VehicleController(displayTable, this));
        idToCRUDControllerMap.put(MODEL_ID, new ModelController(displayTable, this));
        idToCRUDControllerMap.put(BRAND_ID, new BrandController(displayTable, this));
        idToCRUDControllerMap.put(SALES_ID, new TransactionController(displayTable, this));
        idToCRUDControllerMap.put(USER_ID, new UserController(displayTable, this));
    }

    @Override
    public void onUpdateRecord()
    {
        crudController.loadViewTable();
        databaseManager.saveData();
    }
}
