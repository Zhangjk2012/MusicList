package com.musiclist;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.audio.mp4.Mp4TagReader;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyTextInfo;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.mp4.Mp4FieldKey;
import org.jaudiotagger.tag.mp4.Mp4Tag;
import org.jaudiotagger.tag.mp4.Mp4TagField;

public class Test {

    /**
     * @param args
     * @throws InvalidAudioFrameException 
     * @throws ReadOnlyFileException 
     * @throws TagException 
     * @throws IOException 
     */
    public static void main(String[] args) throws Exception {
        
        File f = new File("D:\\1.mp4");
        
        Mp4TagReader m = new Mp4TagReader();
        Mp4Tag t =  m.read(new RandomAccessFile(f, "r"));
        Mp4TagField tf = t.getFirstField(Mp4FieldKey.ARTIST);
        System.out.println(tf);
        
        
        
        
//        AudioFile file = AudioFileIO.read(f);
//        System.out.println(file.getAudioHeader().getTrackLength());
//        
//        MP3File file = new MP3File("D:\\卓依婷-明天会更好.mp3");  
//        MP3AudioHeader audioHeader = (MP3AudioHeader) file.getAudioHeader();
//        System.out.println(audioHeader.getTrackLengthAsString());
////        System.out.println();
//        ID3v23Frame songName=(ID3v23Frame) file.getID3v2Tag().frameMap.get("TIT2");  
//        System.out.println(songName.getContent());
//        
//        ID3v23Frame singer=(ID3v23Frame) file.getID3v2Tag().frameMap.get("TPE1");  
//        ID3v23Frame author=(ID3v23Frame) file.getID3v2Tag().frameMap.get("TALB");
////        ID3v23Frame kk=(ID3v23Frame) file.getID3v2Tag().frameMap.get("YEAR");
////        System.out.println(kk.getContent());
//        
//        System.out.println(singer.getContent());
//        System.out.println(author.getContent());
//        System.out.println("******************************");
//        
//        if (file.hasID3v2Tag()) {
//            AbstractID3v2Tag tag = file.getID3v2Tag();  
//            AbstractID3v2Frame frame = (AbstractID3v2Frame) tag.getFrame(ID3v24Frames.FRAME_ID_ARTIST);  
//            AbstractID3v2Frame frame1 = (AbstractID3v2Frame) tag.getFrame("TRCK");  
//            System.out.println(frame1.getContent());
//            
//            if(frame.getBody() instanceof AbstractFrameBodyTextInfo)
//            {
//                AbstractFrameBodyTextInfo textBody = (AbstractFrameBodyTextInfo)frame.getBody(); 
//                System.out.println(textBody.getText());
//            }
//            
////            FrameBodyAPIC body = (FrameBodyAPIC) frame.getBody();  
////            byte[] imageData = body.getImageData();  
////            Image img=Toolkit.getDefaultToolkit().createImage(imageData, 0,imageData.length);  
////            System.out.println("img----" + imageData);  
////            ImageIcon icon = new ImageIcon(img);              
////            FileOutputStream fos = new FileOutputStream("D://test1.jpg");  
////            fos.write(imageData);  
////            fos.close();  
////            System.out.println("width:"+icon.getIconWidth());  
////            System.out.println("height:"+icon.getIconHeight());  
////            getImg(icon);  
//        }
        
//        System.out.println(tag.getFirstArtist());
//        System.out.println(tag.getFirstAlbum());
//        System.out.println(tag.getFirstTitle());
//        System.out.println(tag.getFirstComment());
//        System.out.println(tag.getFirstYear());
//        System.out.println(tag.getFirstTrack());
        
    }
    public static void getImg(ImageIcon img){  
        JFrame f = new JFrame();  
        JLabel l = new JLabel();  
        l.setIcon(img);  
        l.setVisible(true);  
        f.add(l);  
        f.setSize(500, 500);      
        f.setVisible(true);  
    }
}
