package Interface;

import util.PedidosStatus;
import util.TamanhoPizza;
import util.ClassePizza;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    }
    public Pedido() {
    }

    public int getId() {
        return id;
    }

    public ArrayList<ItemCarrinho> getItens() {
        return new ArrayList<>(itens);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDateTime getDataCriacao() {
        return createdAt;
    }

    private double calcularValorTotal() {
        double total = 0.0;
        for (ItemCarrinho item : itens) {
            total += item.getValor();
        }
        return total;
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
                        Pedido pedido = null;

                        try (FileInputStream fileInputStream = new FileInputStream(file);
                             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
                            pedido = (Pedido) objectInputStream.readObject();
                            Object[] row = {
                                    pedido.getId(),
                                    pedido.getItens(),
                                    pedido.getValorTotal(),
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
            for (int i = 0; i < data.size(); i++) {
                dataArray[i] = data.get(i);
            }

            return dataArray;
        }

    private PedidosStatus getStatus() {
        return this.pedidosStatus;
    }
}
