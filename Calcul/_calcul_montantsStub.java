package Calcul;


/**
* Calcul/_calcul_montantsStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from calcul.idl
* Monday, December 5, 2022 9:25:30 PM CET
*/

public class _calcul_montantsStub extends org.omg.CORBA.portable.ObjectImpl implements Calcul.calcul_montants
{

  public double calcul_ttc (double ht, double tva)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("calcul_ttc", true);
                $out.write_double (ht);
                $out.write_double (tva);
                $in = _invoke ($out);
                double $result = $in.read_double ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return calcul_ttc (ht, tva        );
            } finally {
                _releaseReply ($in);
            }
  } // calcul_ttc

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:Calcul/calcul_montants:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _calcul_montantsStub
