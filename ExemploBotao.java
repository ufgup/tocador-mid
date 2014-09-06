import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import java.io.File;
public class ExemploBotao{
	public static void main(String[] args){
		//Adiciona um evento para ouvir um evento de clic para o botão
		ActionListener SairCallback = new ActionListener() {    //trata o evento que foi gerado
			public void actionPerformed(ActionEvent e) { //sobrecarga
				int resultado = JOptionPane.showOptionDialog(null,"Você realmente deseja sair?","Sair",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,null,null);//monta o dialogo de perguntas
				switch(resultado){
					case JOptionPane.YES_OPTION:
						System.exit(0);
						break;
					case JOptionPane.NO_OPTION:
						break;
			}
		}
	};
	
 ActionListener TocarCallback = new ActionListener() { //novo listening
	 public void actionPerformed(ActionEvent e) { 
		 
		 //criando as especificações do fileChooser
			JFileChooser filechooser = new JFileChooser();
			filechooser.setDialogTitle("Selecione o arquivo");
			filechooser.setCurrentDirectory(new File("examples/"));
			filechooser.setSize(new Dimension(400, 300));
			
			//Verifica o status da operação de escolha do arquivo
			int status = filechooser.showOpenDialog(null);
			switch(status){
				case 1: // ação cancelada
					JOptionPane.showMessageDialog(null, "Nenhum arquivo escolhido!");
					break;
				case 0: // escolhe arquivo valido
					Sequencer player; //o sequenciador midi
					try{
						player = MidiSystem.getSequencer();//inicia o sequenciador
						Sequence musica = MidiSystem.getSequence(filechooser.getSelectedFile()); //carrega musica
						player.open();  //abre o sequenciador
						player.setSequence(musica); //passa a musica que vai ser tocada
						player.setLoopCount(0); //define quantas vezes a musica sera repetida
						player.start(); //toca
					}
					catch(Exception except){
								JOptionPane.showMessageDialog(null, "Erro ao tentar tocar midi!");
					}
				break;		
			}
		}
	};  
		Icon play = new ImageIcon("play.gif"); 
		JButton botao = new JButton("Tocar som",play);
		JButton botaoSair = new JButton("Sair");
		botao.addActionListener(TocarCallback);
		botaoSair.addActionListener(SairCallback);
		
		//cria um painel de divisão dentro da janela
		JPanel painel = new JPanel();
		painel.add(botao);
		painel.add(botaoSair);
		//cria o texto de creditos
		JLabel label1;
		label1 = new JLabel("Desenvolvido por Ana Leticia e Eduardo Lapa");
		label1.setToolTipText("Creditos");
		painel.add(label1);
		
		JFrame janela = new JFrame("Sequenciador MIDI"); // representa a janela
		janela.setResizable(false); //não permite maximizar 
		janela.setLocationRelativeTo(null);  //sempre abrir no centro
		janela.add(painel); //adiciona o painel a janela
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //se o carinha apertar no x fecha o programa
		janela.pack(); //janela ocupa menor espaço
		janela.setVisible(true); //janela esta visivel
		}
}

