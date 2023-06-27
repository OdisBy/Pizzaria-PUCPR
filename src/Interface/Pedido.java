package Interface;

import util.PedidosStatus;

import java.io.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private List<ItemCarrinho> itens;
    private double valorTotal;
    private LocalDateTime createdAt;
    private PedidosStatus pedidosStatus;

    public Pedido(List<ItemCarrinho> itens) {
        this.id = getNextFileId();
        this.itens = new ArrayList<>(itens);
        this.valorTotal = calcularValorTotal();
        this.createdAt = LocalDateTime.now();
        this.pedidosStatus = PedidosStatus.PREPARANDO;
    }
    public Pedido() {
    }

    public static Pedido loadPedidoById(int id) {
        String saveFolder = "saves";
        String fileName = saveFolder + "/" + "pedido_" + id + ".dat";

        Pedido pedido = null;
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            pedido = (Pedido) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro ao ler os dados do pedido do arquivo: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada ao carregar os dados do pedido: " + e.getMessage());
        }

        return pedido;
    }

    public int getId() {
        return id;
    }

    public String getItensAsString() {
        StringBuilder sb = new StringBuilder();
        for (ItemCarrinho item : itens) {
            if(item.getTamanho() != null){
                sb.append(item.getNome() + " (" + item.getTamanho() + ")");
            } else {
                sb.append(item.getNome());
            }

            sb.append(", ");
        }
        if (sb.length() > 2) {
            sb.setLength(sb.length() - 2);
        }

        System.out.println(sb);
        return sb.toString();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setPedidoStatus(PedidosStatus status) {
        this.pedidosStatus = status;
        replaceData(this.id);
    }

    public LocalDateTime getDataCriacao() {
        return createdAt;
    }

    public String getDataCriacaoString() {
        LocalDateTime localDateTime = getDataCriacao();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", new Locale("pt", "BR"));
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }

    private double calcularValorTotal() {
        double total = 0.0;
        for (ItemCarrinho item : itens) {
            total += item.getValor();
        }
        return total;
    }

    private String calcularValorTotalAsString() {
        Double valor = calcularValorTotal();
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        String valorString = decimalFormat.format(valor);
        return valorString;
    }

    public void saveData() {
        String saveFolder = "saves";
        String fileName = saveFolder + "/" + "pedido_" + getNextFileId() + ".dat";

        try {
            try {
                File folder = new File(saveFolder);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro ao criar a pasta: " + saveFolder);
            }

            try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.writeObject(this);

                System.out.println("Dados do pedido foram salvos com sucesso no arquivo: " + fileName);
            } catch (IOException e) {
                System.out.println("Ocorreu um erro ao salvar os dados do pedido no arquivo: " + fileName);
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao salvar os dados: " + e.getLocalizedMessage());
        }
    }

    private void replaceData(int id) {
        String saveFolder = "saves";
        String fileName = saveFolder + "/" + "pedido_" + id + ".dat";

        try{
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }

            try (FileOutputStream fileOut = new FileOutputStream(file);
                 ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
                objectOut.writeObject(this);
                System.out.println("Pedido salvo com sucesso!");
            } catch (IOException e) {
                System.out.println("Erro ao salvar o pedido: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Erro ao substituir data " + e.getMessage());
        }



    }

    private int getNextFileId() {
        File folder = new File("saves");
        File[] files = folder.listFiles();
        int maxId = 0;

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                if (fileName.startsWith("pedido_") && fileName.endsWith(".dat")) {
                    int fileId = Integer.parseInt(fileName.substring(7, fileName.length() - 4));
                    if (fileId > maxId) {
                        maxId = fileId;
                    }
                }
            }
        }

        return maxId + 1;
    }
    public static Object[][] loadAllData() {
        String saveFolder = "saves";
        List<Object[]> data = new ArrayList<>();

        File folder = new File(saveFolder);
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().startsWith("pedido_") && file.getName().endsWith(".dat")) {
                    Pedido pedido;

                    try (FileInputStream fileInputStream = new FileInputStream(file);
                         ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                        pedido = (Pedido) objectInputStream.readObject();
                        Object[] row = {
                                pedido.getId(),
                                pedido.getItensAsString(),
                                pedido.calcularValorTotalAsString(),
                                pedido.getDataCriacaoString(),
                                pedido.getStatus()
                        };
                        data.add(row);
                    } catch (FileNotFoundException e) {
                        System.out.println("Arquivo não encontrado: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Erro ao ler os dados do pedido do arquivo: " + e.getMessage());
                    } catch (ClassNotFoundException e) {
                        System.out.println("Classe não encontrada ao carregar os dados do pedido: " + e.getMessage());
                    }
                }
            }
        }

        if (data.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
        } else {
            System.out.println("Foram encontrados " + data.size() + " pedidos.");
        }

        Object[][] dataArray = new Object[data.size()][];
        for (int i = data.size() - 1; i >= 0; i--) {
            dataArray[i] = data.get(data.size() - 1 - i);
        }
        return dataArray;
    }

    private PedidosStatus getStatus() {
        return this.pedidosStatus;
    }

    public void autoExcluir() {
        String saveFolder = "saves";
        String fileName = saveFolder + "/" + "pedido_" + id + ".dat";
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
